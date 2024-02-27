package com.germanhernandez.rickmortycollection.fake

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.model.Location

val characters = listOf(
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episode = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        )
    )
)

val locations = listOf(
    Location(
        id = 1,
        name = "Earth",
        type = "Planet",
        dimension = "Dimension C-137",
        residents = listOf(
            "https://rickandmortyapi.com/api/character/1",
            "https://rickandmortyapi.com/api/character/2"
        )
    )
)