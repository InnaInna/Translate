package com.translate.di.provider

import com.translate.presentation.main.fragment.translate.TranslateFragment
import com.translate.presentation.main.fragment.worddetails.WordDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {

    @ContributesAndroidInjector
    abstract fun provideTranslateFragment(): TranslateFragment

    @ContributesAndroidInjector
    abstract fun provideWordDetailsFragment(): WordDetailsFragment
}