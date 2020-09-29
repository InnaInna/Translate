package com.translate.presentation.main.fragment.translate

import com.translate.data.entity.presentation.translate.MeaningUIModel
import com.translate.domain.usecase.translate.SearchUseCase
import com.translate.presentation.base.BasePageViewModel
import javax.inject.Inject

class TranslateViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : BasePageViewModel<MeaningUIModel>() {

    private lateinit var searchedWord: String

    fun search(word: String) {
        searchedWord = word
        searchUseCase.setData(searchedWord)
        loadFirstPage(searchUseCase)
    }

    fun loadNextPage() {
        searchUseCase.setData(searchedWord)
        loadNextPage(searchUseCase)
    }
}