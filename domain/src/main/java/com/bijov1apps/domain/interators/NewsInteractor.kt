package com.bijov1apps.domain.interators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.bijov1apps.domain.contracts.PagingSourceFactoryContract
import com.bijov1apps.domain.contracts.RemoteMediatorFactoryContract
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources

//@ExperimentalPagingApi
interface NewsInteractor {

    fun getSourcesDB(callback: (List<Sources>) -> Unit)

//    fun getRemoteMediator(): RemoteMediatorFactoryContract<Articles, RemoteMediator<Int, Articles>>

    fun getPagingSource(): PagingSourceFactoryContract<Articles, PagingSource<Int, Articles>>

    suspend fun getDetailArticle(url: String): Articles

    suspend fun getSourceNameById(sourceId: String): String
}
