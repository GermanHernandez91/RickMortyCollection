package com.germanhernandez.rickmortycollection.domain.use_case.character

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteCharacterByIdUseCase(
    private val repository: CharacterRepository
) {

    operator fun invoke(id: Int): Flow<Character> {
        return repository.getFavouriteCharacterById(id)
    }
}