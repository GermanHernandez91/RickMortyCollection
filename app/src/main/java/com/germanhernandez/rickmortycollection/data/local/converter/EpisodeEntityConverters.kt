package com.germanhernandez.rickmortycollection.data.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EpisodeEntityConverters {

    @TypeConverter
    fun convertToJsonString(episode: List<String>?): String {
        return Json.encodeToString(episode)
    }

    @TypeConverter
    fun convertFromStringToJson(episodesString: String): List<String>? {
        return Json.decodeFromString(episodesString)
    }
}