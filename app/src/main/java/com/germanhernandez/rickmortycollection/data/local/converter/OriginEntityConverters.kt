package com.germanhernandez.rickmortycollection.data.local.converter

import androidx.room.TypeConverter
import com.germanhernandez.rickmortycollection.data.local.entity.LocationEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class OriginEntityConverters {

    @TypeConverter
    fun convertToJsonString(origin: LocationEntity): String {
        return Json.encodeToString(origin)
    }

    @TypeConverter
    fun convertFromStringToJson(originString: String): LocationEntity {
        return Json.decodeFromString(originString)
    }
}