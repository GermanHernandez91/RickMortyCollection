package com.germanhernandez.rickmortycollection.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.germanhernandez.rickmortycollection.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characterEntity: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(characterEntity: CharacterEntity)

    @Query(
        """
            SELECT *
            FROM characters
        """
    )
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    @Query(
        """
            SELECT *
            FROM characters
            WHERE id = :id
        """
    )
    fun getCharacterById(id: Int): Flow<CharacterEntity>
}