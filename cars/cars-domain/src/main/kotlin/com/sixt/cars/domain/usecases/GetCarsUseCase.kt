package com.sixt.cars.domain.usecases

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.entities.Car
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCarsUseCase {

    fun getCars(): Flow<List<Car>>
}

class GetCarsUseCaseImp @Inject constructor(private val repository: CarsRepository) :
    GetCarsUseCase {

    override fun getCars(): Flow<List<Car>> = repository.getCars()

}