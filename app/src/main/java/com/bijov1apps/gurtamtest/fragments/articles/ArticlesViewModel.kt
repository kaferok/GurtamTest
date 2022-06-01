package com.bijov1apps.gurtamtest.fragments.articles

import com.bijov1apps.domain.interators.NewsInteractor
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.gurtamtest.common.BaseViewModel
import com.bijov1apps.gurtamtest.fragments.articles.rv.toRvItem

class ArticlesViewModel(
    sourceId: String,
    private val interactor: NewsInteractor
) : BaseViewModel<ArticlesViewState, ArticlesViewAction>(
    ArticlesViewState()
) {
    init {
        getArticle(sourceId)
    }

    fun onItemClicked(url: String) {
        sendAction(ArticlesViewAction.DetailArticles(url))
    }

    private fun getArticle(sourceId: String) {
        val articles = interactor.getArticlesDB(sourceId) { items ->
            reduceState { oldState ->
                oldState.copy(
                    articles = items.map(Articles::toRvItem),
                    header = items.firstOrNull()?.source?.name.orEmpty()
                )
            }
        }
    }
}