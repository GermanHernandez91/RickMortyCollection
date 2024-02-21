package com.germanhernandez.rickmortycollection.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.germanhernandez.rickmortycollection.data.fake.fakeCharacterEntity
import com.germanhernandez.rickmortycollection.data.local.AppDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class CharacterDaoTest {

    private lateinit var dao: CharacterDao
    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        dao = database.characterDao
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertFavouriteCharacter_returnsTrue() = runBlocking {
        dao.insertCharacter(fakeCharacterEntity)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getAllCharacters().collect {
                assertEquals(it.first(), fakeCharacterEntity)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun deleteFavouriteCharacter_returnsTrue() = runBlocking {
        dao.insertCharacter(fakeCharacterEntity)
        dao.deleteCharacter(fakeCharacterEntity)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getAllCharacters().collect {
                assertEquals(it.count(), 0)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun getAllFavouriteCharacters_returnsResults() = runBlocking {
        dao.insertCharacter(fakeCharacterEntity)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getAllCharacters().collect {
                assertEquals(it.count(), 1)
                assertEquals(it.first(), fakeCharacterEntity)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun getFavouriteCharacterById_returnsResults() = runBlocking {
        dao.insertCharacter(fakeCharacterEntity)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            dao.getCharacterById(1).collect {
                assertEquals(it, fakeCharacterEntity)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }
}