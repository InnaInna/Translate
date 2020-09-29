package com.translate.domain.repository

import androidx.lifecycle.LiveData
import com.translate.common.event.NetworkStateEvent

interface INetworkStateRepository {
    fun showNetworkConnectionError()
    fun showNetworkConnectionErrorDialog()
    fun hideNetworkConnectionError()
    fun observeNetworkConnectionState(): LiveData<NetworkStateEvent>
}