package com.sixt.cars.model.dtos


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarResponseModel(
    @Json(name = "carImageUrl")
    val carImageUrl: String? = null,
    @Json(name = "color")
    val color: String? = null,
    @Json(name = "fuelLevel")
    val fuelLevel: Double? = null,
    @Json(name = "fuelType")
    val fuelType: String? = null,
    @Json(name = "group")
    val group: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "innerCleanliness")
    val innerCleanliness: String? = null,
    @Json(name = "latitude")
    val latitude: Double? = null,
    @Json(name = "licensePlate")
    val licensePlate: String? = null,
    @Json(name = "longitude")
    val longitude: Double? = null,
    @Json(name = "make")
    val make: String? = null,
    @Json(name = "modelIdentifier")
    val modelIdentifier: String? = null,
    @Json(name = "modelName")
    val modelName: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "series")
    val series: String? = null,
    @Json(name = "transmission")
    val transmission: String? = null
)