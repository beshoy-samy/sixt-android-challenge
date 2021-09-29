package com.sixt.cars.domain.contracts

interface Mapper<DTO, ENTITY> {

    fun mapToDomain(dto: DTO): ENTITY
}