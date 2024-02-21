package com.germanhernandez.rickmortycollection.data.mapper

import com.germanhernandez.rickmortycollection.data.local.entity.LocationEntity
import com.germanhernandez.rickmortycollection.data.remote.dto.LocationDto
import com.germanhernandez.rickmortycollection.domain.model.Location

fun Location.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}

fun LocationDto.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}

fun LocationEntity.toLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents
    )
}