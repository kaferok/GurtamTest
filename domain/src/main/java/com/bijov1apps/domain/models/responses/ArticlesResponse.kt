package com.bijov1apps.domain.models.responses

import com.bijov1apps.domain.models.BaseResponse
import com.bijov1apps.domain.models.artilces.Articles
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    override var status: String,
    @SerializedName("totalResult")
    val totalResult: Int,
    @SerializedName("articles")
    val articles: List<Articles>
) : BaseResponse()