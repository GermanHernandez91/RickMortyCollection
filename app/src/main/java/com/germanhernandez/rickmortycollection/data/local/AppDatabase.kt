package com.germanhernandez.rickmortycollection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.germanhernandez.rickmortycollection.data.local.converter.CharacterEntityConverters
import com.germanhernandez.rickmortycollection.data.local.converter.EpisodeEntityConverters
import com.germanhernandez.rickmortycollection.data.local.converter.LocationEntityConverters
import com.germanhernandez.rickmortycollection.data.local.dao.CharacterDao
import com.germanhernandez.rickmortycollection.data.local.dao.EpisodeDao
import com.germanhernandez.rickmortycollection.data.local.dao.LocationDao
import com.germanhernandez.rickmortycollection.data.local.entity.CharacterEntity
import com.germanhernandez.rickmortycollection.data.local.entity.EpisodeEntity
import com.germanhernandez.rickmortycollection.data.local.entity.LocationEntity

@Database(
    entities = [
        CharacterEntity::class,
        LocationEntity::class,
        EpisodeEntity::class
    ],
    version = 1,
)
@TypeConverters(
    CharacterEntityConverters::class,
    LocationEntityConverters::class,
    EpisodeEntityConverters::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract val characterDao: CharacterDao
    abstract val locationDao: LocationDao
    abstract val episodeDao: EpisodeDao
}