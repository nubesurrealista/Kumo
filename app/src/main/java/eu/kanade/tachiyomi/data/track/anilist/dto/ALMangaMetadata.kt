package eu.kanade.tachiyomi.data.track.anilist.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ALMangaMetadata(
    val data: ALMangaMetadataData,
)

@Serializable
data class ALMangaMetadataData(
    @SerialName("Media")
    val media: ALMangaMetadataMedia,
)

@Serializable
data class ALMangaMetadataMedia(
    val id: Long,
    val title: ALItemTitle,
    val coverImage: ItemCover,
    val description: String?,
    val staff: ALStaff,
    val format: String? = null,
    val countryOfOrigin: String? = null,
    val status: String? = null,
    val chapters: Int? = null,
    val averageScore: Int? = null,
    val startDate: ALDate? = null,
)
