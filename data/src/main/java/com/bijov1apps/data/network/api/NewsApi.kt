package com.bijov1apps.data.network.api

import com.bijov1apps.domain.models.responses.ArticlesResponse
import com.bijov1apps.domain.models.responses.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {

    @GET("/v2/everything")
    suspend fun getArticles(): Response<ArticlesResponse>

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(): Response<SourcesResponse>
}
