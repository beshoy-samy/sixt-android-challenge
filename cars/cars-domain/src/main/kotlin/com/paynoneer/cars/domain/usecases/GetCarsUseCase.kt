package com.paynoneer.cars.domain.usecases

import com.paynoneer.cars.domain.contracts.CarsRepository
import com.paynoneer.cars.domain.entities.Car
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetCarsUseCase {

    fun getCars(): Flow<List<Car>>
}

class GetCarsUseCaseImp @Inject constructor(private val repository: CarsRepository) :
    GetCarsUseCase {

    override fun getCars(): Flow<List<Car>> = repository.getCars()

}