package com.bijov1apps.domain.contracts

import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources


interface NewsRepository {

    fun getSourcesDB(callback: (List<Sources>) -> Unit)

//    fun getArticlesPagingSource(): PagingSource<Int, Articles>

    suspend fun getDetailArticle(url: String): Articles

    suspend fun getSourceNameById(sourceId: String): String

}
