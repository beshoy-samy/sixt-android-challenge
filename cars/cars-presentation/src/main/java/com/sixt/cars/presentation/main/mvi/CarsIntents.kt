package com.sixt.cars.presentation.main.mvi

sealed class CarsIntents {
    object InitCars : CarsIntents()
    data class SelectCar(val position: Int) : CarsIntents()
}