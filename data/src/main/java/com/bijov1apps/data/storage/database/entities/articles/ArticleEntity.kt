package com.bijov1apps.data.storage.database.entities.articles

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bijov1apps.domain.models.artilces.Articles

@Entity(tableName = "articles_table")
data class ArticleEntity(
    @Embedded
    val source: SourceEmbedded,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @PrimaryKey
    val url: String,
    @ColumnInfo(name = "image_url")
    val urlToImage: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @ColumnInfo(name = "content")
    val content: String?
)

fun ArticleEntity.toDomainModel() = Articles(
    source.toDomainModel(),
    author,
    title,
    description,
    url,
    urlToImage,
    publishedAt,
    content
)

fun Articles.toEntity() = ArticleEntity(
    source.toEntity(),
    author,
    title,
    description,
    url,
    urlToImage,
    publishedAt,
    content
)