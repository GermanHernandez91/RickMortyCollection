package com.germanhernandez.rickmortycollection.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.germanhernandez.rickmortycollection.data.local.entity.EpisodeEntity
import com.germanhernandez.rickmortycollection.data.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episodeEntity: EpisodeEntity)

    @Delete
    suspend fun deleteEpisode(episodeEntity: EpisodeEntity)

    @Query(
        """
            SELECT *
            FROM episodes
        """
    )
    fun getAllEpisodes(): Flow<List<EpisodeEntity>>

    @Query(
        """
            SELECT *
            FROM episodes
            WHERE id = :id
        """
    )
    fun getEpisodeById(id: Int): Flow<EpisodeEntity>
}