package com.bijov1apps.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bijov1apps.data.storage.database.entities.articles.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ArticleEntity>)

    @Query("SELECT*FROM ARTICLES_TABLE")
    fun getArticles(): Flow<List<ArticleEntity>>
}