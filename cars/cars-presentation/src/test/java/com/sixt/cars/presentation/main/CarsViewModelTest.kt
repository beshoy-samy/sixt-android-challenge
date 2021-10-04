package com.sixt.cars.presentation.main

import com.sixt.cars.domain.entities.Car
import com.sixt.cars.domain.usecases.GetCarsFirstSelectedUseCase
import com.sixt.cars.presentation.main.mvi.CarsIntents
import com.sixt.cars.presentation.main.mvi.CarsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

class CarsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun `send IntentInitCars then emit ViewStateCars after ViewStateCars`() = runBlocking {
        //arrange
        val mockedCars = listOf(mockCar("1", "Jetta"), mockCar("2", "Golf"))

        val getCarsUseCase = object : GetCarsFirstSelectedUseCase {
            override suspend fun getCarsFirstSelected(): Flow<List<Car>> = flow {
                delay(250)
                emit(mockedCars)
            }
        }

        val viewModel = CarsViewModel(getCarsUseCase, Dispatchers.Default)

        //act
        viewModel.intents.send(CarsIntents.InitCars)

        val states = viewModel.viewState.take(3).toList()

        assert(
            states.first() == CarsViewState.Idle && states[1] == CarsViewState.Loading &&
                    states.last() == CarsViewState.Cars(mockedCars)
        )
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun `send IntentInitCars then emit ViewStateError`() = runBlocking {
        //arrange
        val exception = Exception("Network not found")
        val getCarsUseCase = object : GetCarsFirstSelectedUseCase {
            override suspend fun getCarsFirstSelected(): Flow<List<Car>> = flow {
                throw exception
            }
        }

        val viewModel = CarsViewModel(getCarsUseCase, Dispatchers.Default)

        //act
        viewModel.intents.send(CarsIntents.InitCars)

        val states = viewModel.viewState.take(3).toList()

        assert(
            states.first() == CarsViewState.Idle && states[1] == CarsViewState.Loading
                    && states.last() == CarsViewState.Error(exception)
        )
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun `send IntentSelectCar then emit ViewStateCarSelection`() = runBlocking {
        //arrange
        val mockedCars =
            listOf(
                mockCar("1", "Jetta", isSelected = true),
                mockCar("2", "Golf"),
                mockCar("3", "Passat")
            )
        val getCarsUseCase = object : GetCarsFirstSelectedUseCase {
            override suspend fun getCarsFirstSelected(): Flow<List<Car>> = flow {
                delay(250)
                emit(mockedCars)
            }
        }

        val viewModel = CarsViewModel(getCarsUseCase, Dispatchers.Default)

        //act
        viewModel.intents.send(CarsIntents.InitCars)
        viewModel.viewState.take(3).toList()

        viewModel.intents.send(CarsIntents.SelectCar(1))
        val selectCarState = viewModel.viewState.take(2).toList()

        assert(
            selectCarState.last() == CarsViewState.CarSelection(
                0, 1,
                mockCar("2", "Golf", true),
                mockedCars.toMutableList().apply {
                    this[0] = this[0].copy(isSelected = false)
                    this[1] = this[1].copy(isSelected = true)
                }
            )
        )
    }

    private fun mockCar(id: String, name: String, isSelected: Boolean = false) =
        Car(id, name, "", "", "", "", "", "", null, 0.0, 0.0, isSelected)

}