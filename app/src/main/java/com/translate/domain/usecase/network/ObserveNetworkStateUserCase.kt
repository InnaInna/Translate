package com.translate.domain.usecase.network

import androidx.lifecycle.LiveData
import com.translate.common.event.NetworkStateEvent
import com.translate.domain.repository.INetworkStateRepository
import com.translate.domain.usecase.BaseObserveUseCase
import javax.inject.Inject

class ObserveNetworkStateUserCase@Inject constructor(
    private val networkStateRepository: INetworkStateRepository
) : BaseObserveUseCase<NetworkStateEvent>() {

    override fun observe(): LiveData<NetworkStateEvent> =
        networkStateRepository.observeNetworkConnectionState()
}