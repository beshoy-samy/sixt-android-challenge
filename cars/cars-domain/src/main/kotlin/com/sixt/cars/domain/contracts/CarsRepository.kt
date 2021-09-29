package com.sixt.cars.domain.contracts

import com.sixt.cars.domain.entities.Car
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    fun getCars(): Flow<List<Car>>
}