package com.bijov1apps.domain.interators

import androidx.paging.PagingSource
import com.bijov1apps.domain.contracts.NewsRepository
import com.bijov1apps.domain.contracts.PagingSourceFactoryContract
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources

class NewsInteractorImpl(
    private val repository: NewsRepository,
    private val pageSource: PagingSourceFactoryContract<Articles, PagingSource<Int, Articles>>
) : NewsInteractor {
    override fun getArticlesDB(sourceId: String, callback: (List<Articles>) -> Unit) =
        repository.getArticlesDB(sourceId, callback)

    override fun getSourcesDB(callback: (List<Sources>) -> Unit) =
        repository.getSourcesDB(callback)

    override suspend fun getDetailArticle(url: String): Articles = repository.getDetailArticle(url)

    override fun getPagingSource(): PagingSourceFactoryContract<Articles, PagingSource<Int, Articles>> =
        pageSource
}