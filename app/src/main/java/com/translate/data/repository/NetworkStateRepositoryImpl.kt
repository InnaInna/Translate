package com.translate.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.translate.common.NetworkUtils
import com.translate.common.event.NetworkStateEvent
import com.translate.domain.repository.INetworkStateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkStateRepositoryImpl @Inject constructor() : INetworkStateRepository {

    private val networkConnectionStateEvent: MutableLiveData<NetworkStateEvent> = MutableLiveData()

    init {
        networkConnectionStateEvent.value =
            takeIf { NetworkUtils.isOnline() }?.let { NetworkStateEvent.DISCONNECT }
                ?: let { NetworkStateEvent.CONNECT }
    }

    override fun showNetworkConnectionError() {
        CoroutineScope(Dispatchers.Main).launch {
            networkConnectionStateEvent.value = NetworkStateEvent.CONNECT
        }
    }

    override fun showNetworkConnectionErrorDialog() {
        CoroutineScope(Dispatchers.Main).launch {
            networkConnectionStateEvent.value = NetworkStateEvent.ERROR
        }
    }

    override fun hideNetworkConnectionError() {
        CoroutineScope(Dispatchers.Main).launch {
            networkConnectionStateEvent.value = NetworkStateEvent.DISCONNECT
        }
    }

    override fun observeNetworkConnectionState(): LiveData<NetworkStateEvent> =
        networkConnectionStateEvent
}