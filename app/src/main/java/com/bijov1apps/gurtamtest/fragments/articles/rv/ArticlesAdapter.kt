package com.bijov1apps.gurtamtest.fragments.articles.rv

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.bijov1apps.gurtamtest.common.rv.BaseViewHolder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade

class NewsAdapter(
    private val facade: ViewHolderFactoryFacade<ArticlesItem>
) : PagingDataAdapter<ArticlesItem, BaseViewHolder<ArticlesItem>>(ARTICLES_DIFF_UTILS) {
    override fun onBindViewHolder(holder: BaseViewHolder<ArticlesItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticlesItem> {
        return facade.createViewHolder(parent, viewType)
    }
}