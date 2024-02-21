package com.germanhernandez.rickmortycollection.domain.use_case.location

import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository

class AddFavouriteLocationUseCase(
    private val repository: LocationRepository
) {

    suspend operator fun invoke(location: Location) {
        repository.insertFavouriteLocation(location)
    }
}