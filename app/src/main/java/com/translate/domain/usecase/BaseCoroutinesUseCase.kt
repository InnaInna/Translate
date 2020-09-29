package com.translate.domain.usecase

import android.util.Log
import com.translate.common.NetworkUtils.ERROR_NO_INTERNET
import com.translate.data.entity.presentation.NetworkErrorUIModel
import com.translate.domain.repository.INetworkStateRepository
import com.translate.domain.usecase.blok.CompletionBlock
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseCoroutinesUseCase<T> {

    @Inject
    lateinit var networkStateRepository: INetworkStateRepository

    private var parentJob: Job = Job()
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var foregroundContext: CoroutineContext = Dispatchers.Main

    private var delay: Long = 0L

    fun setDelay(delay: Long) {
        this.delay = delay
    }

    protected open suspend fun executeOnBackgroundPre() {
        //do nothing
    }

    protected open fun executePreBackground() {
        //do nothing
    }

    protected open fun executePostBackground() {
        //do nothing
    }

    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }
        unsubscribe()
        parentJob = Job()
        CoroutineScope(foregroundContext + parentJob).launch { launchScope(response) }
    }

    private suspend fun launchScope(response: Request<T>, isRetry: Boolean = true) {
        if (delay > 0) delay(delay)
        executePreBackground()
        try {
            val result = withContext(backgroundContext) {
                executeOnBackgroundPre()
                executeOnBackground()
            }
            response(result)
        } catch (ex: CancellationException) {
            Log.d("translate_log", ex.message)
            response(ex)
        } catch (ex: ConnectException) {
            Log.e("translate_log", ex.message)
            networkStateRepository.showNetworkConnectionErrorDialog()
            response(NetworkErrorUIModel(ERROR_NO_INTERNET, ex.message))
        } catch (ex: UnknownHostException) {
            Log.e("translate_log", ex.message)
            networkStateRepository.showNetworkConnectionErrorDialog()
            response(NetworkErrorUIModel(ERROR_NO_INTERNET, ex.message))
        } catch (ex: HttpException) {
            val error = getError(ex)
            Log.e("translate_log", error.toString())
            response(error)
        } catch (ex: Exception) {
            Log.e("translate_log", ex.message)
            response(ex)
        } finally {
            executePostBackground()
        }
    }

    private fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

    private fun getError(ex: HttpException): NetworkErrorUIModel =
        NetworkErrorUIModel(ex.code(), ex.message())

    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onNetworkError: ((NetworkErrorUIModel) -> Unit)? = null
        private var onError: ((Exception) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onNetworkError(block: (NetworkErrorUIModel) -> Unit) {
            onNetworkError = block
        }

        fun onError(block: (Exception) -> Unit) {
            onError = block
        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }

        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: NetworkErrorUIModel) {
            onNetworkError?.invoke(error)
        }

        operator fun invoke(error: Exception) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}