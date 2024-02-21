package com.germanhernandez.rickmortycollection.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.germanhernandez.rickmortycollection.data.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationEntity: LocationEntity)

    @Delete
    suspend fun deleteLocation(locationEntity: LocationEntity)

    @Query(
        """
            SELECT *
            FROM locations
        """
    )
    fun getAllLocations(): Flow<List<LocationEntity>>

    @Query(
        """
            SELECT *
            FROM locations
            WHERE id = :id
        """
    )
    fun getLocationById(id: Int): Flow<LocationEntity>
}