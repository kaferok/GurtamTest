package com.bijov1apps.gurtamtest.fragments.sources.rv

import android.view.View
import android.view.ViewGroup
import com.bijov1apps.gurtamtest.R
import com.bijov1apps.gurtamtest.common.rv.BaseViewHolder
import com.bijov1apps.gurtamtest.common.rv.ViewHolderFactoryContract
import com.bijov1apps.gurtamtest.common.utils.inflate
import com.bijov1apps.gurtamtest.databinding.SourceItemBinding

class SourcesViewHolder(
    view: View,
    private val onItemClicked: (id: String) -> Unit
) : BaseViewHolder<SourcesItem>(view) {

    private val binding = SourceItemBinding.bind(view)

    override fun bind(item: SourcesItem?) {
        with(binding) {
            tvTitle.text = item?.name
            tvDescription.text = item?.description

            root.setOnClickListener {
                onItemClicked.invoke(item?.id.orEmpty())
            }
        }
    }

    class Factory(
        private val onItemClicked: (id: String) -> Unit
    ) : ViewHolderFactoryContract<SourcesItem> {
        override fun isFor(value: Any): Boolean = value is SourcesItem

        override fun createViewHolder(parent: ViewGroup): BaseViewHolder<SourcesItem> =
            SourcesViewHolder(parent.inflate(R.layout.source_item), onItemClicked)
    }
}