package com.bijov1apps.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "5be22a5559854a86866b8f5e6e0f6a03"

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalUrl = chain.request().url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY)
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl)
        return chain.proceed(newRequest.build())
    }
}
