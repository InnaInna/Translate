package com.translate.presentation.base

import androidx.lifecycle.MutableLiveData
import com.translate.domain.usecase.BasePageCoroutinesUseCase
import com.translate.domain.usecase.network.ObserveNetworkStateUserCase

abstract class BasePageViewModel<T>(
    observeNetworkStateUserCase: ObserveNetworkStateUserCase? = null
) : BaseViewModel(observeNetworkStateUserCase) {

    var isLastPage: Boolean = true
    var isLoading: Boolean = false

    val loadMoreItems: MutableLiveData<List<T>> = MutableLiveData()
    val initItems: MutableLiveData<List<T>> = MutableLiveData()

    protected open fun loadFirstPage(userCase: BasePageCoroutinesUseCase<List<T>>) {
        loadNextPage(userCase, isFirstPage = true)
    }

    protected fun loadNextPage(
        userCase: BasePageCoroutinesUseCase<List<T>>,
        isFirstPage: Boolean = false
    ) {
        if (!isFirstPage) userCase.increasePage() else userCase.resetPage()
        isLoading = true
        userCase.execute {
            onComplete {
                isLastPage = it.size < userCase.getPerPage()
                when (userCase.page) {
                    1L -> initItems.value = it
                    else -> loadMoreItems.value = it
                }
                isLoading = false
            }
            onNetworkError { isLoading = false }
            onError { isLoading = false }
            onCancel { isLoading = false }
        }
    }

    protected fun loadPrevPage(userCase: BasePageCoroutinesUseCase<List<T>>) {
        isLoading = true
        userCase.decreasePage()
        userCase.execute {
            onComplete {
                when (userCase.page) {
                    1L -> initItems.value = it
                    else -> loadMoreItems.value = it
                }
                isLoading = false
                isLastPage = false
            }
            onNetworkError { isLoading = false }
            onError { isLoading = false }
            onCancel { isLoading = false }
        }
    }
}