package com.sixt.cars.domain.usecases

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.entities.Car
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCarsFirstSelectedUseCaseImpTest {

    @Test
    fun `getCarsFirstSelected() then emit listOf Car`() = runBlocking {
        //arrange
        val mockedCars = listOf(mockCar("1", "Golf", isSelected = true), mockCar("2", "Jetta"))

        val carsRepository = object : CarsRepository {
            override suspend fun getCars() = flowOf(mockedCars)
        }

        val carsUseCase = GetCarsFirstSelectedUseCaseImp(carsRepository)

        //act
        val result = carsUseCase.getCarsFirstSelected().first()

        //assert
        assertTrue(result == mockedCars)
    }

    @Test
    fun `getCarsFirstSelected() then emit listOf Car first selected`() = runBlocking {
        //arrange
        val mockedCars = listOf(mockCar("1", "Golf"), mockCar("2", "Jetta"))

        val carsRepository = object : CarsRepository {
            override suspend fun getCars() = flowOf(mockedCars)
        }

        val carsUseCase = GetCarsFirstSelectedUseCaseImp(carsRepository)

        //act
        val result = carsUseCase.getCarsFirstSelected().first()

        //assert
        assertTrue(result.first().isSelected)
    }

    @Test(expected = Exception::class)
    fun `getCarsFirstSelected() then throw Exception`() = runBlocking {
        //arrange
        val carsRepository = object : CarsRepository {
            override suspend fun getCars() = throw Exception("Network Not Found")
        }

        val carsUseCase = GetCarsFirstSelectedUseCaseImp(carsRepository)

        //act
        carsUseCase.getCarsFirstSelected().collect()

        return@runBlocking
    }

    private fun mockCar(id: String, name: String, isSelected: Boolean = false) =
        Car(id, name, "", "", "", "", "", "", null, 0.0, 0.0, isSelected)

}