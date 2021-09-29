package com.paynoneer.cars.domain.entities

data class Car(
    val id: String,
    val name: String,
    val maker: String,
    val group: String,
    val color: String,
    val transmission: String,
    val fuelType: String,
    val licensePlate: String,
    val imageUrl: String?,
    val latitude: Double,
    val longitude: Double
)