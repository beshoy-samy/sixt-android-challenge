package com.sixt.cars.model.datasources

import com.sixt.cars.model.datasources.network.CarsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Singleton
    @Provides
    fun provideCarsService(retrofit: Retrofit): CarsService =
        retrofit.create(CarsService::class.java)

}