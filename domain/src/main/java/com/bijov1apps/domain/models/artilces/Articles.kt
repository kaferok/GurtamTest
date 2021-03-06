package com.bijov1apps.domain.models.artilces

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName("source")
    val source: ArticlesSource = ArticlesSource(),
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val urlToImage: String = "",
    @SerializedName("publishedAt")
    val publishedAt: String = "",
    @SerializedName("content")
    val content: String? = ""
)