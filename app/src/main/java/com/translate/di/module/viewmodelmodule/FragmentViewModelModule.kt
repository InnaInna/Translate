package com.translate.di.module.viewmodelmodule

import androidx.lifecycle.ViewModel
import com.translate.di.mapkey.ViewModelKey
import com.translate.presentation.main.fragment.translate.TranslateViewModel
import com.translate.presentation.main.fragment.worddetails.WordDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FragmentViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TranslateViewModel::class)
    abstract fun bindTranslateViewModel(filterViewModel: TranslateViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WordDetailsViewModel::class)
    abstract fun bindWordDetailsViewModel(filterViewModel: WordDetailsViewModel): ViewModel
}