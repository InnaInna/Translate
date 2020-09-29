package com.translate.data.entity.presentation.meaning

import com.translate.data.entity.presentation.translate.TranslationUIModel

data class FullMeaningUIModel(
    val id: String?,
    val wordId: Int?,
    val wordImgUrl: List<String>?,
    val wordOnEng: String?,
    val translation: TranslationUIModel?,
    val definition: DefinitionUIModel?,
    val examples: List<DefinitionUIModel>?
)