package com.germanhernandez.rickmortycollection.domain.repository

import com.germanhernandez.rickmortycollection.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    suspend fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Result<List<Location>>

    suspend fun getLocationById(id: Int): Result<Location>

    fun getFavouriteLocations(): Flow<List<Location>>

    fun getFavouriteLocationById(id: Int): Flow<Location>

    suspend fun insertFavouriteLocation(location: Location)

    suspend fun deleteFavouriteLocation(location: Location)
}