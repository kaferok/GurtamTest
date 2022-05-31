package com.bijov1apps.data.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bijov1apps.data.storage.database.entities.source.SourcesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<SourcesEntity>)

    @Query("SELECT*FROM SOURCE_TABLE")
    fun getSources(): Flow<List<SourcesEntity>>
}