package eu.kanade.tachiyomi.ui.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import eu.kanade.presentation.components.AppBar
import eu.kanade.presentation.more.storage.StorageScreenContent
import eu.kanade.presentation.more.storage.StorageScreenState
import eu.kanade.presentation.util.Screen
import eu.kanade.tachiyomi.ui.manga.MangaScreen
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.components.material.Scaffold
import tachiyomi.presentation.core.i18n.stringResource
import tachiyomi.presentation.core.screens.LoadingScreen
import uy.kohesive.injekt.api.get
import uy.kohesive.injekt.Injekt

class StorageScreen : Screen() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel = androidx.lifecycle.viewmodel.compose.viewModel {
            Injekt.get<StorageViewModel>()
        }
        val state by viewModel.state.collectAsStateWithLifecycle()
        val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()

        Scaffold(
            topBar = { scrollBehavior ->
                AppBar(
                    title = stringResource(MR.strings.pref_storage_overview),
                    navigateUp = navigator::pop,
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { paddingValues ->
            if (state is StorageScreenState.Loading) {
                LoadingScreen(
                    percentage = (state as StorageScreenState.Loading).progress,
                    message = MR.strings.calculating_storage_overview,
                )
                return@Scaffold
            }

            StorageScreenContent(
                state = state as StorageScreenState.Success,
                selectedCategory = selectedCategory,
                paddingValues = paddingValues,
                onCategorySelected = viewModel::setSelectedCategory,
                onDelete = viewModel::deleteManga,
                onClickCover = { item -> navigator.push(MangaScreen(item.manga.id)) },
            )
        }
    }
}
