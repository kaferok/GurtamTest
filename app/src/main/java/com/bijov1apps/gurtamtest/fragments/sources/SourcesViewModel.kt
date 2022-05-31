package com.bijov1apps.gurtamtest.fragments.sources

import com.bijov1apps.domain.interators.NewsInteractor
import com.bijov1apps.domain.models.sources.Sources
import com.bijov1apps.gurtamtest.common.BaseViewModel
import com.bijov1apps.gurtamtest.fragments.sources.rv.toRvItem

class SourcesViewModel(
    private val interactor: NewsInteractor
) : BaseViewModel<SourcesViewState, SourcesViewAction>(
    initState = SourcesViewState()
) {
    init {
        getSources()
    }

    fun onItemClicked(id: String) {
        sendAction(SourcesViewAction.OpenArticles(id))
    }

    private fun getSources() {
        interactor.getSourcesDB { items ->
            reduceState { oldState -> oldState.copy(sources = items.map(Sources::toRvItem)) }
        }
    }
}