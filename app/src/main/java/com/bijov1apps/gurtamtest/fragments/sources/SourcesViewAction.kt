package com.bijov1apps.gurtamtest.fragments.sources

import com.bijov1apps.gurtamtest.common.ViewAction

sealed class SourcesViewAction : ViewAction {

    data class OpenArticles(val id:String) : SourcesViewAction()
}