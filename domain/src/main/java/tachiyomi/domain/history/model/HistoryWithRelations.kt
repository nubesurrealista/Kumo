package tachiyomi.domain.history.model

import tachiyomi.domain.manga.model.MangaCover
import java.util.Date

data class HistoryWithRelations(
    val id: Long,
    val chapterId: Long,
    val mangaId: Long,
    val title: String,
    val chapterNumber: Double,
    val read: Boolean,
    val lastPageRead: Long,
    val totalChapters: Long,
    val readCount: Long,
    val readAt: Date?,
    val readDuration: Long,
    val coverData: MangaCover,
) {
    val unreadCount: Long
        get() = totalChapters - readCount
}