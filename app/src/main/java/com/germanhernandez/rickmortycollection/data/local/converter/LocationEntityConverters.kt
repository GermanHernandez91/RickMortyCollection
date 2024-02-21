package com.germanhernandez.rickmortycollection.data.local.converter

import androidx.room.TypeConverter
import com.germanhernandez.rickmortycollection.data.local.entity.LocationEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LocationEntityConverters {

    @TypeConverter
    fun convertToJsonString(location: LocationEntity): String {
        return Json.encodeToString(location)
    }

    @TypeConverter
    fun convertFromStringToJson(locationString: String): LocationEntity {
        return Json.decodeFromString(locationString)
    }
}