package com.translate.data.entity.network.translate

import com.google.gson.annotations.SerializedName

data class SearchResponseModel(
    @SerializedName("id") val id: Long,
    @SerializedName("text") val text: String,
    @SerializedName("meanings") val meanings: List<MeaningResponseModel>?
)