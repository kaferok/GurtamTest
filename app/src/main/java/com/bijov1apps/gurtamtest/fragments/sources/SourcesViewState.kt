package com.bijov1apps.gurtamtest.fragments.sources

import com.bijov1apps.gurtamtest.common.ViewState
import com.bijov1apps.gurtamtest.fragments.sources.rv.SourcesItem

data class SourcesViewState(
    val sources: List<SourcesItem> = listOf()
) : ViewState