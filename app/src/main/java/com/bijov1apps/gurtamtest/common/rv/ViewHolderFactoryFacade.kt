package com.bijov1apps.gurtamtest.common.rv

import android.view.ViewGroup

class ViewHolderFactoryFacade<T : BindableItem>(
    private val viewHolderFactories: Array<ViewHolderFactoryContract<out T>>
) : ViewHolderFactoryFacadeContract<T> {

    override fun createViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return viewHolderFactories[viewType].createViewHolder(parent) as BaseViewHolder<T>
    }

    override fun getViewType(item: T): Int {
        viewHolderFactories.forEachIndexed { index, factory ->
            if (factory.isFor(item)) {
                return index
            }
        }
        error("No factory for ${item::class.simpleName}")
    }
}