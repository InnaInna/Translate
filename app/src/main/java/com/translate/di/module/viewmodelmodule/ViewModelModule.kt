package com.translate.di.module.viewmodelmodule

import androidx.lifecycle.ViewModelProvider
import com.translate.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ActivityViewModelModule::class, FragmentViewModelModule::class])
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
