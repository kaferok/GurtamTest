package com.bijov1apps.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bijov1apps.data.network.api.NewsApi
import com.bijov1apps.data.network.bodyOrError
import com.bijov1apps.data.storage.database.dao.ArticlesDao
import com.bijov1apps.data.storage.database.entities.articles.toEntity
import com.bijov1apps.domain.contracts.PagingSourceFactoryContract
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.utils.Result
import retrofit2.HttpException

class NewsPageSource(
    private val api: NewsApi,
    private val articlesDao: ArticlesDao,
    private val source: String? = null
) : PagingSource<Int, Articles>() {

    override fun getRefreshKey(state: PagingState<Int, Articles>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val response = api.getEverything(source, page, pageSize)
        val result = response.bodyOrError()
        return when (result) {
            is Result.Failure -> {
                LoadResult.Error(HttpException(response))
            }
            is Result.Success -> {
                val articles = result.value.articles
                articlesDao.insert(articles.map(Articles::toEntity))

                val nextKey = if (articles.size < pageSize) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                LoadResult.Page(articles, prevKey, nextKey)
            }
        }
    }

    class Factory(
        private val api: NewsApi,
        private val articlesDao: ArticlesDao
    ) :
        PagingSourceFactoryContract<Articles, NewsPageSource> {
        override fun create(source: String): NewsPageSource =
            NewsPageSource(api, articlesDao, source)
    }
}
