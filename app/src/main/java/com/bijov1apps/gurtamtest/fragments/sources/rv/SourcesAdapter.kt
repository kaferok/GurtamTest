package com.bijov1apps.gurtamtest.fragments.sources.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bijov1apps.gurtamtest.common.rv.BaseViewHolder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade

class SourcesAdapter(
    private val facade: ViewHolderFactoryFacade<SourcesItem>
) : ListAdapter<SourcesItem, BaseViewHolder<SourcesItem>>(SOURCES_DIFF_UTILS) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SourcesItem> {
        return facade.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<SourcesItem>, position: Int) {
        holder.bind(getItem(position))
    }
}