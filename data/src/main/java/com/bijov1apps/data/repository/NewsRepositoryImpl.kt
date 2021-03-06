package com.bijov1apps.data.repository

import androidx.paging.PagingSource
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

    private val flowDBScope = CoroutineScope(Dispatchers.IO)

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
            }.launchIn(flowDBScope)
    }

//    override fun getArticlesPagingSource(): PagingSource<Int, Articles> {
//        return articlesDao.getArticles()
//    }

    override suspend fun getDetailArticle(url: String): Articles {
        return articlesDao.getArticle(url)?.toDomainModel() ?: Articles()
    }

    override suspend fun getSourceNameById(sourceId: String): String {
        return sourcesDao.getNameById(sourceId)
    }

    private suspend fun getSources() {
        val result = api.getSources().bodyOrError()
        when (result) {
            is Result.Failure -> {}
            is Result.Success -> sourcesDao.insert(result.value.sources.map(Sources::toEntity))
        }
    }

}
