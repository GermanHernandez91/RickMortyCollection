package com.germanhernandez.rickmortycollection.data.repository

import com.germanhernandez.rickmortycollection.data.local.dao.LocationDao
import com.germanhernandez.rickmortycollection.data.mapper.toLocation
import com.germanhernandez.rickmortycollection.data.mapper.toLocationEntity
import com.germanhernandez.rickmortycollection.data.remote.RickMortyApi
import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationRepositoryImpl(
    private val dao: LocationDao,
    private val api: RickMortyApi
) : LocationRepository {
    override suspend fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Result<List<Location>> {
        return try {
            val locationResultDto = api.getLocations(name, type, dimension)
            Result.success(
                locationResultDto.results.map { it.toLocation() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getLocationById(id: Int): Result<Location> {
        return try {
            val locationDto = api.getSingleLocation(id)
            Result.success(locationDto.toLocation())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override fun getFavouriteLocations(): Flow<List<Location>> {
        return dao
            .getAllLocations()
            .map { entities ->
                entities.map { it.toLocation() }
            }
    }

    override fun getFavouriteLocationById(id: Int): Flow<Location> {
        return dao.getLocationById(id).map { it.toLocation() }
    }

    override suspend fun insertFavouriteLocation(location: Location) {
        dao.insertLocation(location.toLocationEntity())
    }

    override suspend fun deleteFavouriteLocation(location: Location) {
        dao.deleteLocation(location.toLocationEntity())
    }
}