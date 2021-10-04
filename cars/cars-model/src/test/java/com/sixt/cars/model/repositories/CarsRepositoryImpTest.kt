package com.sixt.cars.model.repositories

import com.sixt.cars.domain.entities.Car
import com.sixt.cars.model.datasources.network.CarsNetworkDataSource
import com.sixt.cars.model.dtos.CarResponseModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CarsRepositoryImpTest {

    @Test
    fun `getCars() then emit listOf Car`() = runBlocking {
        //arrange
        val mockedResponse =
            listOf(
                CarResponseModel(id = "1", name = "Golf"),
                CarResponseModel(id = "2", name = "Jetta")
            )
        val mockedCars = listOf(mockCar("1", "Golf"), mockCar("2", "Jetta"))

        val carsNetworkDataSource = object : CarsNetworkDataSource {
            override suspend fun getCars(): List<CarResponseModel> = mockedResponse
        }

        val carsRepository = CarsRepositoryImp(carsNetworkDataSource)

        //act
        val result = carsRepository.getCars().first()

        //assert
        assertTrue(result == mockedCars)
    }

    @Test(expected = Exception::class)
    fun `getCars() then throw Exception`() = runBlocking {
        //arrange
        val carsNetworkDataSource = object : CarsNetworkDataSource {
            override suspend fun getCars() = throw Exception("Network Not Found")
        }

        val carsRepository = CarsRepositoryImp(carsNetworkDataSource)

        //act
        carsRepository.getCars().collect()

        return@runBlocking
    }

    private fun mockCar(id: String, name: String) =
        Car(id, name, "", "", "", "", "", "", null, 0.0, 0.0)

}