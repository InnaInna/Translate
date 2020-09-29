package com.translate.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.translate.di.module.viewmodelmodule.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule{

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources = context.resources
}