package com.bijov1apps.data.di

import android.app.Application
import androidx.room.Room
import com.bijov1apps.data.network.RetrofitBuilder
import com.bijov1apps.data.network.api.NewsApi
import com.bijov1apps.data.repository.NewsRepositoryImpl
import com.bijov1apps.data.storage.database.NewsDatabase
import com.bijov1apps.data.storage.database.dao.ArticlesDao
import com.bijov1apps.data.storage.database.dao.SourcesDao
import com.bijov1apps.domain.contracts.NewsRepository
import org.koin.dsl.module

object DataModule {

    val module = module {
        single<NewsApi> { RetrofitBuilder.build().create(NewsApi::class.java) }

        single<NewsRepository> { NewsRepositoryImpl(get(), get(), get()) }

        //Database
        fun provideDatabase(application: Application): NewsDatabase {
            return Room.databaseBuilder(application, NewsDatabase::class.java, "news_database")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideSourcesDao(database: NewsDatabase): SourcesDao {
            return database.sourcesDao()
        }

        fun provideArticlesDao(database: NewsDatabase): ArticlesDao {
            return database.articlesDao()
        }

        single { provideDatabase(get()) }
        single { provideArticlesDao(get()) }
        single { provideSourcesDao(get()) }
    }
}
