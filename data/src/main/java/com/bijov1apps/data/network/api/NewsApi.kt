package com.bijov1apps.data.network.api

import com.bijov1apps.domain.models.responses.ArticlesResponse
import com.bijov1apps.domain.models.responses.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getArticles(
        @Query("sources") sourceId: String
    ): Response<ArticlesResponse>

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(): Response<SourcesResponse>
}
