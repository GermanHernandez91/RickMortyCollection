package com.germanhernandez.rickmortycollection.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.germanhernandez.rickmortycollection.data.local.converter.LocationEntityConverters
import com.germanhernandez.rickmortycollection.data.local.converter.OriginEntityConverters
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
    @TypeConverters(OriginEntityConverters::class)
    val origin: LocationEntity?,
    @TypeConverters(LocationEntityConverters::class)
    val location: LocationEntity?,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val episode: List<String>? = emptyList()
)
