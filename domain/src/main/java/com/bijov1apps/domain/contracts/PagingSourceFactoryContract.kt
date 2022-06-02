package com.bijov1apps.domain.contracts

import androidx.paging.PagingSource

interface PagingSourceFactoryContract<T : Any, P : PagingSource<Int, T>> {

    fun create(source: String): P
}
