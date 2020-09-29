package com.translate.data.entity.network.meaning

import com.google.gson.annotations.SerializedName

data class DefinitionResponseModel(
    @SerializedName("soundUrl") val soundUrl: String?,
    @SerializedName("text") val text: String?
)