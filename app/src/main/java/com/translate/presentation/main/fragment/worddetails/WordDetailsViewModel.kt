package com.translate.presentation.main.fragment.worddetails

import com.translate.common.SingleLiveData
import com.translate.data.entity.presentation.meaning.FullMeaningUIModel
import com.translate.domain.usecase.meanings.MeaningsUseCase
import com.translate.presentation.base.BaseViewModel
import javax.inject.Inject

class WordDetailsViewModel @Inject constructor(
    private val meaningsUseCase: MeaningsUseCase
) : BaseViewModel() {

    var meaningEvent = SingleLiveData<FullMeaningUIModel>()

    fun getMeaning(id: String) {
        meaningsUseCase.setData(id)
        meaningsUseCase.execute {
            onComplete { meaning -> meaning?.let { meaningEvent.value = it } }
            onNetworkError { errorEvent.value = it.message }
            onError { errorEvent.value = it.message }
        }
    }

    fun navigateBack() {
        navigateUp()
    }
}