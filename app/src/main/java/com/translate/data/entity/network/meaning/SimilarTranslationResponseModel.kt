package com.translate.data.entity.network.meaning

import com.google.gson.annotations.SerializedName
import com.translate.data.entity.network.translate.TranslationResponseModel

data class SimilarTranslationResponseModel(
    @SerializedName("frequencyPercent") val frequencyPercent: String?,
    @SerializedName("meaningId") val meaningId: Int?,
    @SerializedName("partOfSpeechAbbreviation") val partOfSpeechAbbreviation: String?,
    @SerializedName("translation") val translation: TranslationResponseModel?
)