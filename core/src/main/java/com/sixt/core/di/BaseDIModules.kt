package com.sixt.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
object BaseViewModelModule {

    @Provides
    @CoroutineDispatcherMain
    fun provideCoroutineDispatcherMain() = Dispatchers.Main

    @Provides
    @CoroutineDispatcherIO
    fun provideCoroutineDispatcherIO() = Dispatchers.IO

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherMain

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoroutineDispatcherIO