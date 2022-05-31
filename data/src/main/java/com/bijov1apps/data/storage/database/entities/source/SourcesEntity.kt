package com.bijov1apps.data.storage.database.entities.source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bijov1apps.domain.models.sources.Sources

@Entity(tableName = "source_table")
data class SourcesEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "country")
    val country: String
)

fun SourcesEntity.toDomainModel() = Sources(
    id,
    name,
    description,
    url,
    category,
    language,
    country
)

fun Sources.toEntity() = SourcesEntity(
    id,
    name,
    description,
    url,
    category,
    language,
    country
)
