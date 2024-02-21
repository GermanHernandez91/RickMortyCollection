package com.germanhernandez.rickmortycollection.domain.use_case.location

import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavouriteLocationsUseCase(
    private val repository: LocationRepository
) {

    operator fun invoke(): Flow<List<Location>> {
        return repository.getFavouriteLocations()
    }
}