package com.bijov1apps.data.storage.database.entities.remoteKeys

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles_remote_keys_table")
data class ArticlesRemoteKeysEntity(
    @PrimaryKey
    val articleUrl:String,
    val nextKey:Int?,
    val prevKey:Int?
)