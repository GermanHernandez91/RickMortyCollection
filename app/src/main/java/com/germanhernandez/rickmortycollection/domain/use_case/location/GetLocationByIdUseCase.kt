package com.germanhernandez.rickmortycollection.domain.use_case.location

import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository

class GetLocationByIdUseCase(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(id: Int): Result<Location> {
        return repository.getLocationById(id)
    }
}