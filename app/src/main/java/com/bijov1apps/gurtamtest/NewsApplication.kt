package com.bijov1apps.gurtamtest

import android.app.Application
import com.bijov1apps.data.di.DataModule
import com.bijov1apps.domain.di.DomainModule
import com.bijov1apps.gurtamtest.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(DataModule.module + DomainModule.module + AppModule.module)
        }
    }
}