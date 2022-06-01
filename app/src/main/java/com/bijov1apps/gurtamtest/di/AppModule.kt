package com.bijov1apps.gurtamtest.di

import com.bijov1apps.gurtamtest.fragments.articles.ArticlesViewModel
import com.bijov1apps.gurtamtest.fragments.detailArticles.DetailArticleViewModel
import com.bijov1apps.gurtamtest.fragments.everything.EverythingViewModel
import com.bijov1apps.gurtamtest.fragments.sources.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val module = module {
        viewModel { SourcesViewModel(get()) }
        viewModel { (sourceId: String) ->
            ArticlesViewModel(sourceId, get())
        }
        viewModel { (url: String) ->
            DetailArticleViewModel(url, get())
        }
        viewModel { EverythingViewModel(get()) }
    }
}