package com.translate.data.entity.network.meaning

import com.google.gson.annotations.SerializedName
import com.translate.data.entity.network.translate.TranslationResponseModel

data class AlternativeTranslationResponseModel(
    @SerializedName("text") val text: String?,
    @SerializedName("translation") val translation: TranslationResponseModel?
)