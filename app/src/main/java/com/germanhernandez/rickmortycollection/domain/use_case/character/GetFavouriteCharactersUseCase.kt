package com.germanhernandez.rickmortycollection.domain.use_case.character

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteCharactersUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Flow<List<Character>> {
        return repository.getFavouriteCharacters()
    }
}