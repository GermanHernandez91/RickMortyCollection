package com.germanhernandez.rickmortycollection.fake

import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLocationRepositoryImpl : LocationRepository {

    var locationsToReturn = locations
    var errorToReturn: Exception? = null

    override suspend fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Result<List<Location>> {
        return if (errorToReturn != null) {
            Result.failure(errorToReturn!!)
        } else Result.success(locationsToReturn)
    }

    override suspend fun getLocationById(id: Int): Result<Location> {
        return if (errorToReturn != null) {
            Result.failure(errorToReturn!!)
        } else Result.success(locationsToReturn.first())
    }

    override fun getFavouriteLocations(): Flow<List<Location>> = flow {
        emit(locationsToReturn)
    }

    override fun getFavouriteLocationById(id: Int): Flow<Location> = flow {
        emit(locationsToReturn.first())
    }

    override suspend fun insertFavouriteLocation(location: Location) {

    }

    override suspend fun deleteFavouriteLocation(location: Location) {

    }
}