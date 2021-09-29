package com.sixt.cars.model.datasources.network

import com.sixt.cars.model.dtos.CarResponseModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class CarsNetworkDataSourceImpTest {

    @Test
    fun `getCars() then return list of CarResponseModel`() = runBlocking {
        //arrange
        val mockedResponse =
            listOf(
                CarResponseModel(id = "1", name = "Golf"),
                CarResponseModel(id = "2", name = "Jetta")
            )

        val carsService = object : CarsService {
            override suspend fun getCars(): List<CarResponseModel> = mockedResponse

        }

        val carsNetworkDataSource = CarsNetworkDataSourceImp(carsService)

        //act
        val result = carsNetworkDataSource.getCars()

        assertTrue(mockedResponse == result)
    }

    @Test(expected = Exception::class)
    fun `getCars() then throw Exception`() = runBlocking {
        //arrange
        val carsService = object : CarsService {
            override suspend fun getCars() = throw Exception("Network Not Found")

        }

        val carsNetworkDataSource = CarsNetworkDataSourceImp(carsService)

        //act
        carsNetworkDataSource.getCars()

        return@runBlocking
    }
}