package com.germanhernandez.rickmortycollection.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "episodes")
data class EpisodeEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    @ColumnInfo("air_date") val airDate: String? = null,
    val episode: String? = null,
    val characters: List<String>? = emptyList()
)
