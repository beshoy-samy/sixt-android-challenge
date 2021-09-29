package com.sixt.cars.model.repositories

import com.sixt.cars.model.datasources.network.CarsNetworkDataSource
import com.sixt.cars.model.datasources.network.CarsNetworkDataSourceImp
import com.sixt.cars.model.datasources.network.CarsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoriesModule {

    @Provides
    fun provideCarsNetworkDataSource(carsService: CarsService): CarsNetworkDataSource =
        CarsNetworkDataSourceImp(carsService)
}