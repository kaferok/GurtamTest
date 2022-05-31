package com.bijov1apps.gurtamtest.fragments.articles.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bijov1apps.gurtamtest.common.rv.BaseViewHolder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryFacade

class ArticlesAdapter(
    private val facade: ViewHolderFactoryFacade<ArticlesItem>
) : ListAdapter<ArticlesItem, BaseViewHolder<ArticlesItem>>(ARTICLES_DIFF_UTILS) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArticlesItem> = facade.createViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: BaseViewHolder<ArticlesItem>, position: Int) {
        holder.bind(getItem(position))
    }
}