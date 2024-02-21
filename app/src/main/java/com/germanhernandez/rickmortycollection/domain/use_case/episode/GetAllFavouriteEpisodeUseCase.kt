package com.germanhernandez.rickmortycollection.domain.use_case.episode

import com.germanhernandez.rickmortycollection.domain.model.Episode
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class GetAllFavouriteEpisodeUseCase(
    private val repository: EpisodeRepository
) {

    operator fun invoke(): Flow<List<Episode>> {
        return repository.getFavouriteEpisodes()
    }
}