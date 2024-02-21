package com.germanhernandez.rickmortycollection.data.mapper

import com.germanhernandez.rickmortycollection.data.local.entity.CharacterEntity
import com.germanhernandez.rickmortycollection.data.remote.dto.CharacterDto
import com.germanhernandez.rickmortycollection.domain.model.Character

fun Character.toCharacterEntity(): CharacterEntity {
    val originEntity = origin?.toLocationEntity()
    val locationEntity = location?.toLocationEntity()

    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = originEntity,
        location = locationEntity,
        imageUrl = imageUrl,
        episode = episode
    )
}

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status ?: "Unknown",
        species = species ?: "Unknown",
        gender = gender,
        type = type,
        origin = origin?.toLocation(),
        location = location?.toLocation(),
        imageUrl = image ?: "",
        episode = episode
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        type = type,
        origin = origin?.toLocation(),
        location = location?.toLocation(),
        imageUrl = imageUrl,
        episode = episode
    )
}