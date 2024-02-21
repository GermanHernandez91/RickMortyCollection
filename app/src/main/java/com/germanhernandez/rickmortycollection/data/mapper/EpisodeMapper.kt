package com.germanhernandez.rickmortycollection.data.mapper

import com.germanhernandez.rickmortycollection.data.local.entity.EpisodeEntity
import com.germanhernandez.rickmortycollection.data.remote.dto.EpisodeDto
import com.germanhernandez.rickmortycollection.domain.model.Episode

fun Episode.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters
    )
}

fun EpisodeDto.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters
    )
}

fun EpisodeEntity.toEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        airDate = airDate,
        episode = episode,
        characters = characters
    )
}