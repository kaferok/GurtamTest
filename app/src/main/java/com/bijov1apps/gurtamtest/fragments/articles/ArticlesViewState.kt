package com.bijov1apps.gurtamtest.fragments.articles

import com.bijov1apps.gurtamtest.common.ViewState
import com.bijov1apps.gurtamtest.fragments.articles.rv.ArticlesItem

data class ArticlesViewState(
    val articles: List<ArticlesItem> = listOf(),
    val header: String = ""
) : ViewState