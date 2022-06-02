package com.bijov1apps.gurtamtest.fragments.articles.rv

import android.view.View
import android.view.ViewGroup
import com.bijov1apps.domain.utils.DateTimeUtils
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.rv.BaseViewHolder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryContract
import com.bijov1apps.gurtamtest.common.extensions.inflate
import com.bijov1apps.gurtamtest.databinding.ArticlesItemBinding
import com.bumptech.glide.Glide

class ArticlesViewHolder(
    view: View,
    private val onItemClicked: ((url: String) -> Unit)?
) : BaseViewHolder<ArticlesItem>(view) {

    private val binding = ArticlesItemBinding.bind(view)

    override fun bind(item: ArticlesItem?) {
        with(binding) {
            tvTitle.text = item?.title
            tvDate.text = DateTimeUtils.formatPublishedDate(item?.publishedAt)
            tvDescription.text = item?.description

            Glide.with(root)
                .load(item?.urlToImage)
                .into(imgArticle)

            root.setOnClickListener {
                onItemClicked?.invoke(item?.url.orEmpty())
            }
        }
    }

    class Factory(
        private val onItemClicked: ((url: String) -> Unit)? = null
    ) : ViewHolderFactoryContract<ArticlesItem> {
        override fun isFor(value: Any): Boolean = value is ArticlesItem

        override fun createViewHolder(parent: ViewGroup): BaseViewHolder<ArticlesItem> =
            ArticlesViewHolder(parent.inflate(R.layout.articles_item), onItemClicked)

    }
}