package com.translate.data.entity.network.translate

import com.google.gson.annotations.SerializedName

data class TranslationResponseModel(
    @SerializedName("text") val text: String?,
    @SerializedName("note") val note: String?
)