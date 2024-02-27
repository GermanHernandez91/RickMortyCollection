package com.germanhernandez.rickmortycollection.domain.use_case

class FilterEpisodeUseCase {

    operator fun invoke(episodes: List<String>): List<String> {
        return episodes.map { it.substring(it.lastIndexOf("/") + 1) }
    }
}