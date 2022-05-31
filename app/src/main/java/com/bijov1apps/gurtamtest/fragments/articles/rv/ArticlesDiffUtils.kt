package com.bijov1apps.gurtamtest.fragments.articles.rv

import androidx.recyclerview.widget.DiffUtil

val ARTICLES_DIFF_UTILS = object : DiffUtil.ItemCallback<ArticlesItem>() {
    override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean =
        oldItem == newItem
}