package com.paynoneer.cars.domain.contracts

import com.paynoneer.cars.domain.entities.Car
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    fun getCars(): Flow<List<Car>>
}