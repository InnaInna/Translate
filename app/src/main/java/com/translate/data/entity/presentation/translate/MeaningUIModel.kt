package com.translate.data.entity.presentation.translate

data class MeaningUIModel(
    val id: Long,
    val textOnEng: String?,
    val partOfSpeechCode: String?,
    val translation: TranslationUIModel?,
    val previewUrl: String?,
    val imageUrl: String?,
    val transcription: String?,
    val soundUrl: String?
)