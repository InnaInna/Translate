package com.translate.domain.usecase.blok

import com.translate.domain.usecase.BaseCoroutinesUseCase

typealias CompletionBlock<T> = BaseCoroutinesUseCase.Request<T>.() -> Unit