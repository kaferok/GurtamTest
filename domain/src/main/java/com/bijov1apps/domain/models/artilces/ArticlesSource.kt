package com.bijov1apps.domain.models.artilces

import com.google.gson.annotations.SerializedName

data class ArticlesSource(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)