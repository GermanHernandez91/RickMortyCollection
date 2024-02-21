package com.germanhernandez.rickmortycollection.domain.use_case.character

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository

class DeleteFavouriteCharacterUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(character: Character) {
        repository.deleteFavouriteCharacter(character)
    }
}