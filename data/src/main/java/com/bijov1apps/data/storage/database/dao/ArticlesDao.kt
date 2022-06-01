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

    @Query("SELECT*FROM ARTICLES_TABLE WHERE source_id == :sourceId")
    fun getArticles(sourceId: String): Flow<List<ArticleEntity>>

    @Query("SELECT*FROM ARTICLES_TABLE WHERE url == :url LIMIT 1")
    suspend fun getArticle(url: String): ArticleEntity?
}
