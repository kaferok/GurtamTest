package com.bijov1apps.gurtamtest.fragments.detailArticles

import com.bijov1apps.domain.models.artilces.Articles
import com.bijov1apps.gurtamtest.common.ViewState

data class DetailArticleViewState(
    val article: Articles? = Articles()
) : ViewState