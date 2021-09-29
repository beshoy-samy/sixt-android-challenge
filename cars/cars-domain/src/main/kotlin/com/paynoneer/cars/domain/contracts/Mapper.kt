package com.paynoneer.cars.domain.contracts

interface Mapper<DTO, ENTITY> {

    fun mapToDomain(dto: DTO): ENTITY
}