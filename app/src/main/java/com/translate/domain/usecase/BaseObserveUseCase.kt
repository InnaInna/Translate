package com.translate.domain.usecase

import androidx.lifecycle.LiveData

abstract class BaseObserveUseCase<T> {

    abstract fun observe(): LiveData<T>
}