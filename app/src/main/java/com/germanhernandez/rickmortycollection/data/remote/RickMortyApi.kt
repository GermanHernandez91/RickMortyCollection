package com.germanhernandez.rickmortycollection.data.remote

import com.germanhernandez.rickmortycollection.data.remote.dto.CharacterDto
import com.germanhernandez.rickmortycollection.data.remote.dto.CharacterResultDto
import com.germanhernandez.rickmortycollection.data.remote.dto.EpisodeDto
import com.germanhernandez.rickmortycollection.data.remote.dto.EpisodeResultDto
import com.germanhernandez.rickmortycollection.data.remote.dto.LocationDto
import com.germanhernandez.rickmortycollection.data.remote.dto.LocationResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("name") name: String?,
        @Query("status") status: String?,
        @Query("species") species: String?,
        @Query("type") type: String?,
        @Query("gender") gender: String?
    ): CharacterResultDto

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): CharacterDto

    @GET("location")
    suspend fun getLocations(
        @Query("name") name: String?,
        @Query("type") type: String?,
        @Query("dimension") dimension: String?
    ): LocationResultDto

    @GET("location/{id}")
    suspend fun getSingleLocation(
        @Path("id") id: Int
    ): LocationDto

    @GET("episode")
    suspend fun getEpisodes(
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): EpisodeResultDto

    @GET("episode/{id}")
    suspend fun getSingleEpisode(
        @Path("id") id: Int
    ): EpisodeDto

    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}