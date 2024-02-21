package com.germanhernandez.rickmortycollection.domain.use_case.episode

import com.germanhernandez.rickmortycollection.domain.model.Episode
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteEpisodeByIdUseCase(
    private val repository: EpisodeRepository
) {

    operator fun invoke(id: Int): Flow<Episode> {
        return repository.getFavouriteEpisodeById(id)
    }
}