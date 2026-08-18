package eu.kanade.tachiyomi.ui.storage

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.util.fastDistinctBy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hippo.unifile.UniFile
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import eu.kanade.domain.manga.interactor.UpdateManga
import eu.kanade.presentation.more.storage.StorageScreenState
import eu.kanade.presentation.more.storage.data.StorageData
import eu.kanade.tachiyomi.data.download.DownloadCache
import eu.kanade.tachiyomi.data.download.DownloadManager
import eu.kanade.tachiyomi.source.Source
import eu.kanade.tachiyomi.util.storage.size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.update
import tachiyomi.core.common.util.lang.launchIO
import tachiyomi.core.common.util.lang.launchNonCancellable
import tachiyomi.domain.category.interactor.GetCategories
import tachiyomi.domain.category.model.Category
import tachiyomi.domain.manga.interactor.GetLibraryManga
import tachiyomi.domain.manga.model.Manga
import tachiyomi.domain.source.service.SourceManager
import tachiyomi.source.local.io.Archive
import tachiyomi.source.local.io.LocalSourceFileSystem
import tachiyomi.source.local.isLocal
import kotlin.random.Random

@Inject
@ViewModelKey
@ContributesIntoMap(AppScope::class, binding = binding<ViewModel>())
class StorageViewModel(
    private val downloadCache: DownloadCache,
    private val downloadManager: DownloadManager,
    private val getLibraryManga: GetLibraryManga,
    private val getCategories: GetCategories,
    private val updateManga: UpdateManga,
    private val sourceManager: SourceManager,
    private val sourceFileSystem: LocalSourceFileSystem,
) : ViewModel() {

    private val _state = MutableStateFlow<StorageScreenState>(StorageScreenState.Loading(0))
    val state = _state.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Category>(allCategory)
    val selectedCategory = _selectedCategory.asStateFlow()

    private val skipDownloadChangeFlow = MutableStateFlow(false)
    private val _downloadedItems = MutableStateFlow<Pair<List<StorageData>, List<Category>>>(
        emptyList<StorageData>() to emptyList(),
    )
    val downloadedItems = _downloadedItems.asStateFlow()

    private val entries = MutableStateFlow<List<Long>>(emptyList())

    init {
        viewModelScope.launchIO {
            val downloadCacheFlow = downloadCache.changes
                .debounce(500L)
                .transformLatest {
                    if (skipDownloadChangeFlow.value) {
                        skipDownloadChangeFlow.value = false
                        return@transformLatest
                    } else {
                        emit(Unit)
                    }
                }

            combine(
                downloadCacheFlow,
                downloadCache.isInitializing,
                getLibraryManga.subscribe().distinctUntilChanged { old, new ->
                    old.map { Pair(it.id, it.categories.toSortedSet()) }.toSet() ==
                        new.map { Pair(it.id, it.categories.toSortedSet()) }.toSet()
                },
                getCategories.subscribe(),
            ) { _, _, libraries, categories ->
                val distinctEntries = libraries.fastDistinctBy { it.id }

                if (downloadedItems.value.first.isNotEmpty() && distinctEntries.size < entries.value.size) {
                    val (items, categories) = downloadedItems.value
                    val libraryIds = libraries.map { it.manga.id }
                    val newItems = items.filter { it.manga.id in libraryIds }

                    entries.value = distinctEntries.map { it.id }

                    return@combine newItems to categories
                }

                entries.value = distinctEntries.map { it.id }

                val items = mutableListOf<StorageData>()

                _state.update {
                    StorageScreenState.Loading(0)
                }

                distinctEntries.forEachIndexed { index, libraryManga ->
                    val manga = libraryManga.manga
                    val random = Random(manga.id)

                    val size = getSize(manga)
                    val chapterCount = getCount(manga)
                    val categories = getMangaCategoryIds(manga)

                    _state.update {
                        StorageScreenState.Loading((((index + 1.0) / distinctEntries.size) * 100).toInt())
                    }

                    if (size > 0) {
                        items.add(
                            StorageData(
                                manga = manga,
                                categories = categories,
                                size = size,
                                chapterCount = chapterCount,
                                color = Color(
                                    random.nextInt(255),
                                    random.nextInt(255),
                                    random.nextInt(255),
                                ),
                            ),
                        )
                    }
                }
                items to listOf(allCategory) + categories
            }
                .collectLatest {
                    _downloadedItems.value = it
                }
        }

        combine(
            downloadedItems,
            selectedCategory,
        ) { (items, categories), selectedCategory ->
            val filteredItems = if (selectedCategory.id == allCategory.id) {
                items
            } else {
                items.filter { item ->
                    item.categories.contains(selectedCategory.id)
                }
            }
                .sortedByDescending { it.size }

            filteredItems to categories
        }
            .onEach { (items, categories) ->
                if (items.isEmpty() && categories.isEmpty()) return@onEach

                _state.update {
                    StorageScreenState.Success(
                        items = items,
                        categories = categories,
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private suspend fun getMangaCategoryIds(manga: Manga): List<Long> {
        return getCategories.await(manga.id)
            .map { it.id }
    }

    private fun getSize(manga: Manga): Long {
        return if (manga.isLocal()) {
            sourceFileSystem
                .getMangaDirectory(manga.url)
                ?.size()
                ?: 0L
        } else {
            downloadManager.getDownloadSize(manga)
        }
    }

    private fun getCount(manga: Manga): Int {
        return if (manga.isLocal()) {
            sourceFileSystem
                .getFilesInMangaDirectory(manga.url)
                .count { Archive.isSupported(it) }
        } else {
            downloadManager.getDownloadCount(manga)
        }
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory.update { category }
    }

    fun deleteManga(storageData: StorageData, removeFromLibrary: Boolean) {
        val manga = storageData.manga

        viewModelScope.launchNonCancellable {
            skipDownloadChangeFlow.value = true

            if (manga.isLocal()) {
                sourceFileSystem
                    .getMangaDirectory(manga.url)
                    ?.delete()
            } else {
                val source = sourceManager.get(manga.source) ?: return@launchNonCancellable
                downloadManager.deleteManga(manga, source)
            }

            if (removeFromLibrary) {
                updateManga.awaitUpdateFavorite(storageData.manga.id, false)
            }
        }

        _downloadedItems.update { (items, categories) ->
            items.filterNot { it.manga.id == manga.id } to categories
        }
    }

    companion object {
        const val ALL_CATEGORY_ID = -1L

        val allCategory = Category(
            id = ALL_CATEGORY_ID,
            name = "All",
            order = 0L,
            flags = 0L,
        )
    }
}
