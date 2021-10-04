package com.sixt.cars.model.repositories

import com.sixt.cars.domain.contracts.CarsRepository
import com.sixt.cars.domain.entities.Car
import com.sixt.cars.model.datasources.network.CarsNetworkDataSource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class CarsRepositoryImp @Inject constructor(private val carsNetworkDataSource: CarsNetworkDataSource) :
    CarsRepository {

    override suspend fun getCars() =
        flow {
            val response = carsNetworkDataSource.getCars()
            emit(response)
        }.map {
            it.map { carResponse ->
                Car(
                    carResponse.id.orEmpty(),
                    carResponse.name.orEmpty(),
                    carResponse.make.orEmpty(),
                    carResponse.group.orEmpty(),
                    carResponse.color.orEmpty(),
                    carResponse.transmission.orEmpty(),
                    carResponse.fuelType.orEmpty(),
                    carResponse.licensePlate.orEmpty(),
                    carResponse.carImageUrl,
                    carResponse.latitude ?: 0.0,
                    carResponse.longitude ?: 0.0
                )
            }
        }

}