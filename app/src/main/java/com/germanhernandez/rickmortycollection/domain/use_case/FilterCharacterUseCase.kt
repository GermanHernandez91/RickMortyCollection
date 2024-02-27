package com.germanhernandez.rickmortycollection.domain.use_case

import com.germanhernandez.rickmortycollection.domain.model.Character

class FilterCharacterUseCase {

    operator fun invoke(
        locationName: String,
        characters: List<Character>
    ): List<Character> {
        return characters.filter { (it.location?.name ?: "").contains(locationName) }
    }
}