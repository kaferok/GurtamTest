package com.bijov1apps.gurtamtest.fragments.sources.rv

import androidx.recyclerview.widget.DiffUtil

val SOURCES_DIFF_UTILS = object : DiffUtil.ItemCallback<SourcesItem>() {
    override fun areItemsTheSame(oldItem: SourcesItem, newItem: SourcesItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SourcesItem, newItem: SourcesItem): Boolean =
        oldItem == newItem

}