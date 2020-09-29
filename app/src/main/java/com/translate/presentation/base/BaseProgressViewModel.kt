package com.translate.presentation.base

import androidx.lifecycle.Observer
import com.translate.common.SingleLiveData
import com.translate.common.event.StateEvent
import com.translate.domain.usecase.network.ObserveNetworkStateUserCase
import com.translate.domain.usecase.progress.ObserveProgressUserCase

abstract class BaseProgressViewModel(
    observeProgressUserCase: ObserveProgressUserCase,
    observeNetworkStateUserCase: ObserveNetworkStateUserCase? = null
) : BaseViewModel(observeNetworkStateUserCase) {

    val progressGlobalEvent = SingleLiveData<StateEvent>()

    private val progressObserve = Observer<StateEvent> {
        progressGlobalEvent.value = it
    }

    init {
        observeProgressUserCase.apply { observe().observeForever(progressObserve) }
    }
}