package com.translate.domain.usecase.progress

import androidx.lifecycle.LiveData
import com.translate.common.event.StateEvent
import com.translate.domain.repository.IProgressRepository
import com.translate.domain.usecase.BaseObserveUseCase
import javax.inject.Inject

class ObserveProgressUserCase @Inject constructor(
    private val progressRepository: IProgressRepository
) : BaseObserveUseCase<StateEvent>() {

    override fun observe(): LiveData<StateEvent> = progressRepository.observeProgressState()
}