package com.bijov1apps.gurtamtest.fragments.sources.rv

import com.bijov1apps.domain.models.sources.Sources
import com.bijov1apps.gurtamtest.common.rv.BindableItem

data class SourcesItem(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) : BindableItem

fun Sources.toRvItem() = SourcesItem(
    id, name, description, url, category, language, country
)