package com.translate.di.module

import com.translate.data.repository.NetworkStateRepositoryImpl
import com.translate.data.repository.ProgressRepositoryImpl
import com.translate.data.repository.TranslateRepositoryImpl
import com.translate.domain.repository.INetworkStateRepository
import com.translate.domain.repository.IProgressRepository
import com.translate.domain.repository.ITranslateRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsProgressRepository(repository: ProgressRepositoryImpl): IProgressRepository

    @Binds
    @Singleton
    abstract fun bindsNetworkStateRepository(repository: NetworkStateRepositoryImpl): INetworkStateRepository

    @Binds
    @Singleton
    abstract fun bindsTranslateRepository(repository: TranslateRepositoryImpl): ITranslateRepository
}