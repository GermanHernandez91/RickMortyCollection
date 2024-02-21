package com.germanhernandez.rickmortycollection.data.di

import android.app.Application
import androidx.room.Room
import com.germanhernandez.rickmortycollection.data.local.AppDatabase
import com.germanhernandez.rickmortycollection.data.remote.RickMortyApi
import com.germanhernandez.rickmortycollection.data.repository.CharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.data.repository.EpisodeRepositoryImpl
import com.germanhernandez.rickmortycollection.data.repository.LocationRepositoryImpl
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "rick_morty_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRickMortyApi(client: OkHttpClient): RickMortyApi {
        return Retrofit.Builder()
            .baseUrl(RickMortyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickMortyApi, database: AppDatabase): CharacterRepository {
        return CharacterRepositoryImpl(database.characterDao, api)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(api: RickMortyApi, database: AppDatabase): LocationRepository {
        return LocationRepositoryImpl(database.locationDao, api)
    }

    @Provides
    @Singleton
    fun provideEpisodeRepository(api: RickMortyApi, database: AppDatabase): EpisodeRepository {
        return EpisodeRepositoryImpl(database.episodeDao, api)
    }
}