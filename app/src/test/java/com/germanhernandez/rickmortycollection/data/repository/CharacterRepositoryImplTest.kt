package com.germanhernandez.rickmortycollection.data.repository

import com.germanhernandez.rickmortycollection.data.remote.RickMortyApi
import com.germanhernandez.rickmortycollection.data.remote.invalidCharacterByIdResponse
import com.germanhernandez.rickmortycollection.data.remote.invalidCharacterResponse
import com.germanhernandez.rickmortycollection.data.remote.validCharacterByIdResponse
import com.germanhernandez.rickmortycollection.data.remote.validCharacterResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CharacterRepositoryImplTest {

    private lateinit var repository: CharacterRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: RickMortyApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(RickMortyApi::class.java)
        repository = CharacterRepositoryImpl(
            dao = mockk(relaxed = true),
            api = api
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get Characters, valid response, returns results`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validCharacterResponse)
        )
        val result = repository.getAllCharacters(
            page = 1,
            name = null,
            status = null,
            species = null,
            type = null,
            gender = null
        )

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Get Characters, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validCharacterResponse)
        )

        val result = repository.getAllCharacters(
            page = 1,
            name = null,
            status = null,
            species = null,
            type = null,
            gender = null
        )

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Get Characters, valid response, using filters, returns results`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validCharacterResponse)
        )
        val result = repository.getAllCharacters(
            page = 1,
            name = "Rick Sanchez",
            status = null,
            species = null,
            type = null,
            gender = null
        )

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Get Characters, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(invalidCharacterResponse)
        )
        val result = repository.getAllCharacters(
            page = 1,
            name = "Rick Sanchez",
            status = null,
            species = null,
            type = null,
            gender = null
        )

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Get Character by id, valid response, returns character`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validCharacterByIdResponse)
        )
        val result = repository.getCharacterById(1)

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Get Character by id, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(invalidCharacterByIdResponse)
        )
        val result = repository.getCharacterById(1)

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Get Character by id, invalid id, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody(validCharacterByIdResponse)
        )
        val result = repository.getCharacterById(0)

        assertThat(result.isFailure).isTrue()
    }
}