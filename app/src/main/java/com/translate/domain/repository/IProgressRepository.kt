package com.translate.domain.repository

import androidx.lifecycle.LiveData
import com.translate.common.event.StateEvent

interface IProgressRepository {

    fun showProgressBar(obj: Any)
    fun hideProgressBar(obj: Any)
    fun observeProgressState(): LiveData<StateEvent>
}