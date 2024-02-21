package com.germanhernandez.rickmortycollection.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.germanhernandez.rickmortycollection.data.local.converter.CharacterEntityConverters
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    @ColumnInfo("air_date") val airDate: String? = null,
    val episode: String? = null,
    @TypeConverters(CharacterEntityConverters::class)
    val characters: List<CharacterEntity>? = emptyList()
)
