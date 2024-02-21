package com.germanhernandez.rickmortycollection.domain.use_case.episode

import com.germanhernandez.rickmortycollection.domain.model.Episode
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository

class GetAllEpisodesUseCase(
    private val repository: EpisodeRepository
) {

    suspend operator fun invoke(
        name: String?,
        episode: String?
    ): Result<List<Episode>> {
        return repository.getAllEpisodes(name, episode)
    }
}