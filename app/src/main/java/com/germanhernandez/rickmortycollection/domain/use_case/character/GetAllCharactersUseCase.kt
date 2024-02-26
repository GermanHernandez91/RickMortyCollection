package com.germanhernandez.rickmortycollection.domain.use_case.character

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository

class GetAllCharactersUseCase(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(
        page: Int,
        name: String?,
        status: String?,
        type: String?,
        species: String?
    ): Result<List<Character>> {
        return repository.getAllCharacters(page, name, status, species, type, status)
    }
}