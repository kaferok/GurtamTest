package com.bijov1apps.domain.interators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import com.bijov1apps.domain.contracts.NewsRepository
import com.bijov1apps.domain.contracts.PagingSourceFactoryContract
import com.bijov1apps.domain.contracts.RemoteMediatorFactoryContract
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources

class NewsInteractorImpl(
    private val repository: NewsRepository,
//    private val remoteMediator: RemoteMediatorFactoryContract<Articles, RemoteMediator<Int, Articles>>,
    private val pagingSource: PagingSourceFactoryContract<Articles, PagingSource<Int, Articles>>
) : NewsInteractor {

    override fun getSourcesDB(callback: (List<Sources>) -> Unit) =
        repository.getSourcesDB(callback)

//    override fun getRemoteMediator(): RemoteMediatorFactoryContract<Articles, RemoteMediator<Int, Articles>> =
//        remoteMediator

    override fun getPagingSource(): PagingSourceFactoryContract<Articles, PagingSource<Int, Articles>> =
        pagingSource

    override suspend fun getDetailArticle(url: String): Articles = repository.getDetailArticle(url)

    override suspend fun getSourceNameById(sourceId: String): String =
        repository.getSourceNameById(sourceId)
}