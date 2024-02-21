package com.germanhernandez.rickmortycollection.domain.repository

import com.germanhernandez.rickmortycollection.domain.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {

    suspend fun getAllEpisodes(
        name: String?,
        episode: String?
    ): Result<List<Episode>>

    suspend fun getEpisodeById(id: Int): Result<Episode>

    fun getFavouriteEpisodes(): Flow<List<Episode>>

    fun getFavouriteEpisodeById(id: Int): Flow<Episode>

    suspend fun insertFavouriteEpisode(episode: Episode)

    suspend fun deleteFavouriteEpisode(episode: Episode)
}