package com.bijov1apps.gurtamtest.common.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : BindableItem>(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: T?) = Unit
}
