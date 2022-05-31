package com.bijov1apps.gurtamtest.fragments.articles.rv

import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.gurtamtest.common.rv.BindableItem

data class ArticlesItem(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?
) : BindableItem

fun Articles.toRvItem() = ArticlesItem(
    author,
    title,
    description,
    url,
    urlToImage,
    publishedAt,
    content
)