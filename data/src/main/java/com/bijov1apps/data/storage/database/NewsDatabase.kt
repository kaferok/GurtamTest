package com.bijov1apps.data.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bijov1apps.data.storage.database.dao.ArticlesDao
import com.bijov1apps.data.storage.database.dao.SourcesDao
import com.bijov1apps.data.storage.database.entities.articles.ArticleEntity
import com.bijov1apps.data.storage.database.entities.remoteKeys.ArticlesRemoteKeysEntity
import com.bijov1apps.data.storage.database.entities.source.SourcesEntity

@Database(entities = [SourcesEntity::class, ArticleEntity::class,ArticlesRemoteKeysEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun sourcesDao(): SourcesDao

    abstract fun articlesDao(): ArticlesDao
}