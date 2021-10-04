package com.sixt.cars.domain.usecases

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.entities.Car
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetCarsFirstSelectedUseCase {

    suspend fun getCarsFirstSelected(): Flow<List<Car>>

}

class GetCarsFirstSelectedUseCaseImp @Inject constructor(private val repository: CarsRepository) :
    GetCarsFirstSelectedUseCase {

    override suspend fun getCarsFirstSelected(): Flow<List<Car>> = repository.getCars()
        .map { cars ->
            val mutable = cars.toMutableList()
            mutable.firstOrNull()?.let {
                mutable[0] = it.copy(isSelected = true)
            }
            mutable
        }

}