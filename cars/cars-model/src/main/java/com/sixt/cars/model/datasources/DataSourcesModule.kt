package com.sixt.cars.model.datasources

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.model.datasources.network.CarsNetworkDataSource
import com.sixt.cars.model.repositories.CarsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataSourcesModule {

    @Provides
    fun provideCarsRepository(carsNetworkDataSource: CarsNetworkDataSource): CarsRepository =
        CarsRepositoryImp(carsNetworkDataSource)
}