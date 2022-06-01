package com.bijov1apps.domain.contracts

import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources


interface NewsRepository {

    fun getArticlesDB(sourceId:String, callback: (List<Articles>) -> Unit)

    fun getSourcesDB(callback: (List<Sources>) -> Unit)

    suspend fun getDetailArticle(url: String): Articles

}
