package com.bijov1apps.gurtamtest.di

import com.bijov1apps.gurtamtest.fragments.sources.SourcesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val module = module {
        viewModel { SourcesViewModel(get()) }
    }
}