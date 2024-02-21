package com.germanhernandez.rickmortycollection.domain.use_case.location

import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository

class GetAllLocationsUseCase(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(
        name: String?,
        type: String?,
        dimension: String?
    ): Result<List<Location>> {
        return repository.getAllLocations(name, type, dimension)
    }
}