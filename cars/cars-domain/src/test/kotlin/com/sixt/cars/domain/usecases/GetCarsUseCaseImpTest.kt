package com.sixt.cars.domain.usecases

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.entities.Car
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetCarsUseCaseImpTest {

    @Test
    fun `getCars() then emit listOf Car`() = runBlocking {
        //arrange
        val mockedCars = listOf(mockCar("1", "Golf"), mockCar("2", "Jetta"))

        val carsRepository = object : CarsRepository {
            override suspend fun getCars() = flowOf(mockedCars)
        }

        val carsUseCase = GetCarsUseCaseImp(carsRepository)

        //act
        val result = carsUseCase.getCars().first()

        //assert
        assertTrue(result == mockedCars)
    }

    @Test(expected = Exception::class)
    fun `getCars() then throw Exception`() = runBlocking {
        //arrange
        val carsRepository = object : CarsRepository {
            override suspend fun getCars() = throw Exception("Network Not Found")
        }

        val carsUseCase = GetCarsUseCaseImp(carsRepository)

        //act
        carsUseCase.getCars().collect()

        return@runBlocking
    }

    private fun mockCar(id: String, name: String) =
        Car(id, name, "", "", "", "", "", "", null, 0.0, 0.0)
}