package com.translate.common

import android.content.Context
import android.net.ConnectivityManager
import com.translate.TranslateApp

object NetworkUtils {

    const val ERROR_INTERNAL_SERVER = 500
    const val ERROR_ENTITY = 422
    const val ERROR_BAD_REQUEST = 400
    const val ERROR_ACCESS_DENIED = 401
    const val ERROR_FORBIDDEN = 403
    const val ERROR_NOT_FOUND = 404
    const val ERROR_NO_INTERNET = 0x7

    fun isOnline(): Boolean {
        val context = TranslateApp.appComponent.context()
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return cm?.activeNetworkInfo?.isConnected == true
    }
}