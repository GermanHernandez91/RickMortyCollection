package com.germanhernandez.rickmortycollection.data.repository

import com.germanhernandez.rickmortycollection.data.local.dao.EpisodeDao
import com.germanhernandez.rickmortycollection.data.mapper.toEpisode
import com.germanhernandez.rickmortycollection.data.mapper.toEpisodeEntity
import com.germanhernandez.rickmortycollection.data.remote.RickMortyApi
import com.germanhernandez.rickmortycollection.domain.model.Episode
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpisodeRepositoryImpl(
    private val dao: EpisodeDao,
    private val api: RickMortyApi
) : EpisodeRepository {
    override suspend fun getAllEpisodes(name: String?, episode: String?): Result<List<Episode>> {
        return try {
            val episodeResultDto = api.getEpisodes(name, episode)
            Result.success(
                episodeResultDto.results.map { it.toEpisode() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getEpisodeById(id: Int): Result<Episode> {
        return try {
            val episodeDto = api.getSingleEpisode(id)
            Result.success(episodeDto.toEpisode())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override fun getFavouriteEpisodes(): Flow<List<Episode>> {
        return dao
            .getAllEpisodes()
            .map { entities ->
                entities.map { it.toEpisode() }
            }
    }

    override fun getFavouriteEpisodeById(id: Int): Flow<Episode> {
        return dao.getEpisodeById(id).map { it.toEpisode() }
    }

    override suspend fun insertFavouriteEpisode(episode: Episode) {
        dao.insertEpisode(episode.toEpisodeEntity())
    }

    override suspend fun deleteFavouriteEpisode(episode: Episode) {
        dao.deleteEpisode(episode.toEpisodeEntity())
    }
}