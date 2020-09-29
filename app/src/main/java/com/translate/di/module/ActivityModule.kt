package com.translate.di.module

import com.translate.di.provider.MainActivityProvider
import com.translate.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityProvider::class])
    abstract fun bindMainActivity(): MainActivity
}