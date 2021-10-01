package com.sixt.cars.presentation.main.mvi

import com.sixt.cars.domain.entities.Car

sealed class CarsViewState {
    object MapState : CarsViewState()
    object Loading : CarsViewState()
    data class Error(val throwable: Throwable) : CarsViewState()
    data class Cars(val data: List<Car>) : CarsViewState()
    data class CarSelection(
        val oldSelection: Int,
        val position: Int,
        val car: Car,
        val cars: List<Car>
    ) : CarsViewState()
}
