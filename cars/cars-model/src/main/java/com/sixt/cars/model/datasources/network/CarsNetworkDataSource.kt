package com.sixt.cars.model.datasources.network

import com.sixt.cars.model.dtos.CarResponseModel
import javax.inject.Inject

interface CarsNetworkDataSource {

    suspend fun getCars(): List<CarResponseModel>
}

internal class CarsNetworkDataSourceImp @Inject constructor(private val service: CarsService) :
    CarsNetworkDataSource {

    override suspend fun getCars(): List<CarResponseModel> = service.getCars()

}