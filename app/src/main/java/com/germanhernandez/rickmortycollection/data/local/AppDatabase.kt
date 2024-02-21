package com.germanhernandez.rickmortycollection.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
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
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val characterDao: CharacterDao
    abstract val locationDao: LocationDao
    abstract val episodeDao: EpisodeDao
}