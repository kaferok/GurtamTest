package com.bijov1apps.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bijov1apps.data.storage.database.entities.remoteKeys.ArticlesRemoteKeysEntity

@Dao
interface ArticlesRemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<ArticlesRemoteKeysEntity>)

    @Query("SELECT*FROM ARTICLES_REMOTE_KEYS_TABLE WHERE articleUrl == :articleUrl")
    suspend fun remoteKeysByArticleUrl(articleUrl: String): ArticlesRemoteKeysEntity

    @Query("DELETE FROM ARTICLES_REMOTE_KEYS_TABLE")
    suspend fun clear()
}