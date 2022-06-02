package com.bijov1apps.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.bijov1apps.data.network.api.NewsApi
import com.bijov1apps.data.network.bodyOrError
import com.bijov1apps.data.storage.database.dao.ArticlesDao
import com.bijov1apps.data.storage.database.dao.ArticlesRemoteKeysDao
import com.bijov1apps.data.storage.database.entities.articles.toEntity
import com.bijov1apps.data.storage.database.entities.remoteKeys.ArticlesRemoteKeysEntity
import com.bijov1apps.domain.contracts.RemoteMediatorFactoryContract
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.utils.Result
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val api: NewsApi,
    private val articlesDao: ArticlesDao,
    private val articlesRemoteKeysDao: ArticlesRemoteKeysDao,
    private val source: String? = null
) : RemoteMediator<Int, Articles>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Articles>
    ): MediatorResult {

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(true)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(true)
            }
        }
        val pageSize = state.config.pageSize

        val response = api.getEverything(source, currentPage, pageSize)
        val result = response.bodyOrError()
        return when (result) {
            is Result.Failure -> MediatorResult.Error(HttpException(response))
            is Result.Success -> {
                val articles = result.value.articles
                val articlesSize = articles.size
                val endOfPaginationReached = articlesSize < state.config.pageSize

                if (loadType == LoadType.REFRESH) {
                    articlesDao.clear()
                    articlesRemoteKeysDao.clear()
                }
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1
                val keys = articles.map {
                    ArticlesRemoteKeysEntity(
                        it.url,
                        nextKey,
                        prevKey
                    )
                }
                articlesRemoteKeysDao.insert(keys)
                articlesDao.insert(articles.map(Articles::toEntity))
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Articles>): ArticlesRemoteKeysEntity? {
        return state.lastItemOrNull()?.let { article ->
            articlesRemoteKeysDao.remoteKeysByArticleUrl(article.url)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Articles>): ArticlesRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.url?.let { url ->
                articlesRemoteKeysDao.remoteKeysByArticleUrl(url)
            }
        }
    }

    class Factory(
        private val api: NewsApi,
        private val articlesDao: ArticlesDao,
        private val articlesRemoteKeysDao: ArticlesRemoteKeysDao,
    ) : RemoteMediatorFactoryContract<Articles, NewsRemoteMediator> {
        override fun create(source: String): NewsRemoteMediator =
            NewsRemoteMediator(api, articlesDao, articlesRemoteKeysDao, source)
    }
}
