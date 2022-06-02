package com.bijov1apps.domain.contracts

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator

@ExperimentalPagingApi
interface RemoteMediatorFactoryContract<T : Any, P : RemoteMediator<Int, T>> {

    fun create(source: String): P
}
