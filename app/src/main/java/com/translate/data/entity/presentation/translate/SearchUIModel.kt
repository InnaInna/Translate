package com.translate.data.entity.presentation.translate

data class SearchUIModel(
    val id: Long,
    val text: String?,
    val meanings: List<MeaningUIModel>?
)