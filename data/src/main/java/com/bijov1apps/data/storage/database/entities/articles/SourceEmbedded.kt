package com.bijov1apps.data.storage.database.entities.articles

import androidx.room.ColumnInfo
import com.bijov1apps.domain.models.artilces.ArticlesSource

data class SourceEmbedded(
    @ColumnInfo(name = "id")
    val id: String?,
    @ColumnInfo(name = "name")
    val name: String?
)

fun SourceEmbedded.toDomainModel() = ArticlesSource(
    id, name
)

fun ArticlesSource.toEntity() = SourceEmbedded(
    id, name
)
