package com.bijov1apps.gurtamtest.base.rv

import android.view.ViewGroup

interface ViewHolderFactoryContract<T : BindableItem> {

    fun isFor(value: Any): Boolean

    fun createViewHolder(parent: ViewGroup): BaseViewHolder<T>
}