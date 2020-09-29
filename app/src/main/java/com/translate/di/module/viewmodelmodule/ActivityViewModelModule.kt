package com.translate.di.module.viewmodelmodule

import androidx.lifecycle.ViewModel
import com.translate.di.mapkey.ViewModelKey
import com.translate.presentation.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}