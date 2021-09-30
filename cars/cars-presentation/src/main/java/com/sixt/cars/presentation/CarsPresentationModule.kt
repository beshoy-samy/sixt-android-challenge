package com.sixt.cars.presentation

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.usecases.GetCarsUseCase
import com.sixt.cars.domain.usecases.GetCarsUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CarsPresentationModule {

    @Provides
    fun provideGetCarsUseCase(carsRepository: CarsRepository): GetCarsUseCase {
        return GetCarsUseCaseImp(carsRepository)
    }
}