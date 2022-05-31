package com.bijov1apps.data.network

import com.bijov1apps.data.BuildConfig
import com.bijov1apps.data.network.interceptors.ApiKeyInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT_VALUE: Long = 60

object RetrofitBuilder {

    internal fun build(): Retrofit {
        val client = OkHttpClient.Builder().apply {
            addInterceptor(ApiKeyInterceptor())
            connectTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            callTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS)
        }.callTimeout(TIMEOUT_VALUE, TimeUnit.SECONDS).build()
        return build(client)
    }

    private fun build(client: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.SERVER_URL)
        .client(client)
        .build()
}
