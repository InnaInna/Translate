package com.translate

import com.translate.di.ApplicationComponent
import com.translate.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TranslateApp : DaggerApplication() {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
    }
}