package com.bijov1apps.data.repository

import com.bijov1apps.data.network.api.NewsApi
import com.bijov1apps.data.network.bodyOrError
import com.bijov1apps.data.storage.database.dao.ArticlesDao
import com.bijov1apps.data.storage.database.dao.SourcesDao
import com.bijov1apps.data.storage.database.entities.articles.ArticleEntity
import com.bijov1apps.data.storage.database.entities.articles.toDomainModel
import com.bijov1apps.data.storage.database.entities.articles.toEntity
import com.bijov1apps.data.storage.database.entities.source.SourcesEntity
import com.bijov1apps.data.storage.database.entities.source.toDomainModel
import com.bijov1apps.data.storage.database.entities.source.toEntity
import com.bijov1apps.domain.contracts.NewsRepository
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources
import com.bijov1apps.domain.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val api: NewsApi,
    private val articlesDao: ArticlesDao,
    private val sourcesDao: SourcesDao
) : NewsRepository {

    override fun getArticlesDB(callback: (List<Articles>) -> Unit) {
        articlesDao.getArticles()
            .distinctUntilChanged()
            .map { it.map(ArticleEntity::toDomainModel) }
            .onEach { items ->
                if (items.isEmpty()) {
                    getArticles(0)
                } else {
                    callback(items)
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun getSourcesDB(callback: (List<Sources>) -> Unit) {
        sourcesDao.getSources()
            .distinctUntilChanged()
            .map { it.map(SourcesEntity::toDomainModel) }
            .onEach { items ->
                if (items.isEmpty()) {
                    getSources()
                } else {
                    callback(items)
                }
            }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    private suspend fun getArticles(page: Int) {
        val result = api.getArticles().bodyOrError()
        when (result) {
            is Result.Failure -> {}
            is Result.Success -> articlesDao.insert(result.value.articles.map(Articles::toEntity))
        }
    }

    private suspend fun getSources() {
        val result = api.getSources().bodyOrError()
        when (result) {
            is Result.Failure -> {}
            is Result.Success -> sourcesDao.insert(result.value.sources.map(Sources::toEntity))
        }
    }

}
