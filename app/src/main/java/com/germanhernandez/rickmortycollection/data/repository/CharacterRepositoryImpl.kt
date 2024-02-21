package com.germanhernandez.rickmortycollection.data.repository

import com.germanhernandez.rickmortycollection.data.local.dao.CharacterDao
import com.germanhernandez.rickmortycollection.data.mapper.toCharacter
import com.germanhernandez.rickmortycollection.data.mapper.toCharacterEntity
import com.germanhernandez.rickmortycollection.data.remote.RickMortyApi
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val dao: CharacterDao,
    private val api: RickMortyApi
) : CharacterRepository {
    override suspend fun getAllCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): Result<List<Character>> {
        return try {
            val characterResultDto = api.getCharacters(name, status, species, type, gender)
            Result.success(
                characterResultDto.results.map { it.toCharacter() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getCharacterById(id: Int): Result<Character> {
        return try {
            val characterDto = api.getSingleCharacter(id)
            Result.success(characterDto.toCharacter())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override fun getFavouriteCharacters(): Flow<List<Character>> {
        return dao
            .getAllCharacters()
            .map { entities ->
                entities.map { it.toCharacter() }
            }
    }

    override fun getFavouriteCharacterById(id: Int): Flow<Character> {
        return dao.getCharacterById(id).map { it.toCharacter() }
    }

    override suspend fun insertFavouriteCharacter(character: Character) {
        dao.insertCharacter(character.toCharacterEntity())
    }

    override suspend fun deleteFavouriteCharacter(character: Character) {
        dao.deleteCharacter(character.toCharacterEntity())
    }
}