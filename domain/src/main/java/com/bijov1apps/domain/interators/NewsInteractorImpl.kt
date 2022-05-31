package com.bijov1apps.domain.interators

import com.bijov1apps.domain.contracts.NewsRepository
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.domain.models.sources.Sources

class NewsInteractorImpl(
    private val repository: NewsRepository
) : NewsInteractor {
    override fun getArticlesDB(callback: (List<Articles>) -> Unit) =
        repository.getArticlesDB(callback)

    override fun getSourcesDB(callback: (List<Sources>) -> Unit) =
        repository.getSourcesDB(callback)
}