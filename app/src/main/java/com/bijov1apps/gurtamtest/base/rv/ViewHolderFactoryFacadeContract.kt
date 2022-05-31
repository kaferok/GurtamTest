package com.bijov1apps.gurtamtest.base.rv

import android.view.ViewGroup

interface ViewHolderFactoryFacadeContract<T : BindableItem> {

    fun createViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T>

    fun getViewType(item: T): Int
}