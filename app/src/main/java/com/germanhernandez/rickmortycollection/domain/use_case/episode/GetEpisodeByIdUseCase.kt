package com.germanhernandez.rickmortycollection.domain.use_case.episode

import com.germanhernandez.rickmortycollection.domain.model.Episode
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository

class GetEpisodeByIdUseCase(
    private val repository: EpisodeRepository
) {

    suspend operator fun invoke(id: Int): Result<Episode> {
        return repository.getEpisodeById(id)
    }
}