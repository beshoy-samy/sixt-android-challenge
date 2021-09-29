package com.sixt.cars.domain.usecases

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.entities.Car
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCarsUseCase {

    suspend fun getCars(): Flow<List<Car>>
}

class GetCarsUseCaseImp @Inject constructor(private val repository: CarsRepository) :
    GetCarsUseCase {

    override suspend fun getCars(): Flow<List<Car>> = repository.getCars()

}