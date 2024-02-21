package com.germanhernandez.rickmortycollection.domain.use_case.episode

import com.germanhernandez.rickmortycollection.domain.model.Episode
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository

class DeleteFavouriteEpisodeUseCase(
    private val repository: EpisodeRepository
) {

    suspend operator fun invoke(episode: Episode) {
        repository.deleteFavouriteEpisode(episode)
    }
}