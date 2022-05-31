package com.bijov1apps.domain.di

import com.bijov1apps.domain.interators.NewsInteractor
import com.bijov1apps.domain.interators.NewsInteractorImpl
import org.koin.dsl.module

object DomainModule {

    val module = module {
        single<NewsInteractor> { NewsInteractorImpl(get()) }
    }
}