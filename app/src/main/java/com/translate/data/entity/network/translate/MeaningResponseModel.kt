package com.translate.data.entity.network.translate

import com.google.gson.annotations.SerializedName

data class MeaningResponseModel(
    @SerializedName("id") val id: Long,
    @SerializedName("partOfSpeechCode") val partOfSpeechCode: String?,
    @SerializedName("translation") val translation: TranslationResponseModel?,
    @SerializedName("previewUrl") val previewUrl: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("transcription") val transcription: String?,
    @SerializedName("soundUrl") val soundUrl: String?
)