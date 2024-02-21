package com.germanhernandez.rickmortycollection.data.local.converter

import androidx.room.TypeConverter
import com.germanhernandez.rickmortycollection.data.local.entity.CharacterEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CharacterEntityConverters {

    @TypeConverter
    fun convertToJsonString(character: CharacterEntity): String {
        return Json.encodeToString(character)
    }

    @TypeConverter
    fun convertFromStringToJson(characterString: String): CharacterEntity {
        return Json.decodeFromString(characterString)
    }
}