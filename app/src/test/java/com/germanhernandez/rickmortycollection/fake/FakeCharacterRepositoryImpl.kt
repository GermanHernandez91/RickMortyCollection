package com.germanhernandez.rickmortycollection.fake

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeCharacterRepositoryImpl : CharacterRepository {

    var charactersToReturn = characters
    var errorToReturn: Exception? = null

    override suspend fun getAllCharacters(
        page: Int,
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Result<List<Character>> {
        return if (errorToReturn != null) {
            Result.failure(errorToReturn!!)
        } else Result.success(characters)
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        return if (errorToReturn != null) {
            Result.failure(errorToReturn!!)
        } else Result.success(characters.first())
    }

    override fun getFavouriteCharacters(): Flow<List<Character>> = flow {
        emit(charactersToReturn)
    }

    override fun getFavouriteCharacterById(id: Int): Flow<Character> = flow {
        emit(charactersToReturn.first())
    }

    override suspend fun insertFavouriteCharacter(character: Character) {

    }

    override suspend fun deleteFavouriteCharacter(character: Character) {

    }
}