package com.germanhernandez.rickmortycollection.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val status: String,
    val species: String,
    val type: String? = null,
    val gender: String,
    val origin: LocationEntity?,
    val location: LocationEntity?,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val episode: List<String>? = emptyList()
)
