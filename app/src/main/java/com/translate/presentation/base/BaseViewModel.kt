package com.translate.presentation.base

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.translate.common.Navigate
import com.translate.common.SingleLiveData
import com.translate.common.event.NetworkStateEvent
import com.translate.domain.usecase.network.ObserveNetworkStateUserCase

abstract class BaseViewModel(
    observeNetworkStateUserCase: ObserveNetworkStateUserCase? = null
) : ViewModel() {

    val navigationGlobalEvent = SingleLiveData<Navigate.Global>()
    val networkConnectionGlobalEvent = SingleLiveData<NetworkStateEvent>()
    val errorEvent = SingleLiveData<String?>()

    private val networkConnectionObserve = Observer<NetworkStateEvent> {
        networkConnectionGlobalEvent.value = it
    }

    init {
        observeNetworkStateUserCase?.observe()?.observeForever(networkConnectionObserve)
    }

    protected fun navigateTo(event: Navigate.Global) {
        navigationGlobalEvent.value = event
    }

    protected fun navigateUp() {
        navigationGlobalEvent.value = Navigate.Global.Back
    }

    protected fun showError(message: String? = null) {
        errorEvent.value = message
    }
}