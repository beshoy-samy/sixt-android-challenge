package com.sixt.cars.model.datasources.network

import com.sixt.cars.model.dtos.CarResponseModel
import retrofit2.http.GET

interface CarsService {

    @GET("codingtask/cars")
    suspend fun getCars(): List<CarResponseModel>
}