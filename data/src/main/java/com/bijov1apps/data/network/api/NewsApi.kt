package com.bijov1apps.data.network.api

import com.bijov1apps.domain.models.responses.ArticlesResponse
import com.bijov1apps.domain.models.responses.SourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val DEFAULT_PAGE_SIZE = 30

interface NewsApi {

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(): Response<SourcesResponse>

    @GET("/v2/everything")
    suspend fun getEverything(
        @Query("sources") sources: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE
    ): Response<ArticlesResponse>
}
