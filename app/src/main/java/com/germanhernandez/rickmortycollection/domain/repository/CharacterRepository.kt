package com.germanhernandez.rickmortycollection.domain.repository

import com.germanhernandez.rickmortycollection.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getAllCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Result<List<Character>>

    suspend fun getCharacterById(id: Int): Result<Character>

    fun getFavouriteCharacters(): Flow<List<Character>>

    fun getFavouriteCharacterById(id: Int): Flow<Character>

    suspend fun insertFavouriteCharacter(character: Character)

    suspend fun deleteFavouriteCharacter(character: Character)
}