package com.bijov1apps.gurtamtest.fragments.everything

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bijov1apps.domain.interators.NewsInteractor
import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.gurtamtest.common.BaseViewModel
import com.bijov1apps.gurtamtest.common.ViewAction
import com.bijov1apps.gurtamtest.common.ViewState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

private const val QUERY = "bitcoin"

class EverythingViewModel(
    private val interactor: NewsInteractor
) : BaseViewModel<ViewState, ViewAction>(
    initState = object : ViewState {}
) {

    val newsEverything: StateFlow<PagingData<Articles>> =
        Pager(PagingConfig(pageSize = 5)) {
            interactor.getPagingSource().create(QUERY)
        }
            .flow
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())


}