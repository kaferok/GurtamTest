package com.bijov1apps.domain.models.responses

import com.bijov1apps.domain.models.BaseResponse
import com.bijov1apps.domain.models.sources.Sources
import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    override var status: String,
    @SerializedName("sources")
    val sources: List<Sources>
) : BaseResponse()