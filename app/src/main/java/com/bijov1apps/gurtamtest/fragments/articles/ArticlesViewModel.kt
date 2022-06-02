package com.bijov1apps.gurtamtest.fragments.articles

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bijov1apps.domain.interators.NewsInteractor
import com.bijov1apps.gurtamtest.common.BaseViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//@ExperimentalPagingApi
class ArticlesViewModel(
    sourceId: String,
    private val interactor: NewsInteractor
) : BaseViewModel<ArticlesViewState, ArticlesViewAction>(
    ArticlesViewState()
) {

    init {
        getHeader(sourceId)
    }

    val newsEverything =
        Pager(
            PagingConfig(pageSize = 5),
//            remoteMediator = interactor.getRemoteMediator().create(sourceId)
        ) {
            interactor.getPagingSource().create(sourceId)
        }
            .flow
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    fun onItemClicked(url: String) {
        sendAction(ArticlesViewAction.DetailArticles(url))
    }

    private fun getHeader(sourceId: String) {
        viewModelScope.launch {
            val header = interactor.getSourceNameById(sourceId)
            reduceState { oldState -> oldState.copy(header = header) }
        }
    }

}
