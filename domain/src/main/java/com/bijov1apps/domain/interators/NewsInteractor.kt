package com.bijov1apps.domain.interators

import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources

interface NewsInteractor {

    fun getArticlesDB(sourceId:String, callback: (List<Articles>) -> Unit)

    fun getSourcesDB(callback: (List<Sources>) -> Unit)
}
