package com.germanhernandez.rickmortycollection.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val type: String? = null,
    val dimension: String? = null,
    val residents: List<CharacterEntity>? = emptyList()
)
