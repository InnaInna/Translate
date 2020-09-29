package com.translate.di

import android.app.Application
import android.content.Context
import com.translate.TranslateApp
import com.translate.di.module.ActivityModule
import com.translate.di.module.ApplicationModule
import com.translate.di.module.NetworkModule
import com.translate.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityModule::class,
        NetworkModule::class, RepositoryModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<TranslateApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun context(): Context
}
