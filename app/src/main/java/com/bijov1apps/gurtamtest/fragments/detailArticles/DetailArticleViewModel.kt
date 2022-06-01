package com.bijov1apps.gurtamtest.fragments.detailArticles

import androidx.lifecycle.viewModelScope
import com.bijov1apps.domain.interators.NewsInteractor
import com.bijov1apps.gurtamtest.common.BaseViewModel
import com.bijov1apps.gurtamtest.common.ViewAction
import kotlinx.coroutines.launch

class DetailArticleViewModel(
    url: String,
    private val interactor: NewsInteractor
) : BaseViewModel<DetailArticleViewState, ViewAction>(
    initState = DetailArticleViewState()
) {
    init {
        getArticle(url)
    }

    private fun getArticle(url: String) {
        viewModelScope.launch {
            val article = interactor.getDetailArticle(url)
            reduceState { oldState -> oldState.copy(article = article) }
        }
    }
}