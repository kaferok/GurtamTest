package com.bijov1apps.gurtamtest.fragments.articles

import com.bijov1apps.gurtamtest.common.ViewAction

sealed class ArticlesViewAction : ViewAction {

    data class DetailArticles(val url: String) : ArticlesViewAction()
}