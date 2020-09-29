package com.translate.data.entity.network.meaning

import com.google.gson.annotations.SerializedName
import com.translate.data.entity.network.translate.TranslationResponseModel

data class FullMeaningResponseModel(
    @SerializedName("alternativeTranslations") val alternativeTranslations: List<AlternativeTranslationResponseModel>?,
    @SerializedName("definition") val definition: DefinitionResponseModel?,
    @SerializedName("difficultyLevel") val difficultyLevel: Int?,
    @SerializedName("examples") val examples: List<DefinitionResponseModel>?,
    @SerializedName("id") val id: String?,
    @SerializedName("images") val images: List<ImageResponseModel>?,
    @SerializedName("meaningsWithSimilarTranslation") val meaningsWithSimilarTranslation: List<SimilarTranslationResponseModel>?,
    @SerializedName("mnemonics") val mnemonics: Any?,
    @SerializedName("partOfSpeechCode") val partOfSpeechCode: String?,
    @SerializedName("prefix") val prefix: Any?,
    @SerializedName("properties") val properties: PropertiesResponseModel?,
    @SerializedName("soundUrl") val soundUrl: String?,
    @SerializedName("text") val text: String?,
    @SerializedName("transcription") val transcription: String?,
    @SerializedName("translation") val translation: TranslationResponseModel?,
    @SerializedName("updatedAt") val updatedAt: String?,
    @SerializedName("wordId") val wordId: Int?
)