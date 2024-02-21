package com.germanhernandez.rickmortycollection.domain.di

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.repository.EpisodeRepository
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.EpisodeUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.LocationUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.character.AddFavouriteCharacterUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.DeleteFavouriteCharacterUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetCharacterByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetFavouriteCharacterByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetFavouriteCharactersUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.AddFavouriteEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.DeleteFavouriteEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetAllEpisodesUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetAllFavouriteEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetEpisodeByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.episode.GetFavouriteEpisodeByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.AddFavouriteLocationUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.DeleteFavouriteLocationUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetAllFavouriteLocationsUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetAllLocationsUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetFavouriteLocationByIdUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetLocationByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppDomainModule {

    @ViewModelScoped
    @Provides
    fun provideCharacterUseCases(repository: CharacterRepository): CharacterUseCases {
        return CharacterUseCases(
            addFavouriteCharacterUseCase = AddFavouriteCharacterUseCase(repository),
            deleteFavouriteCharacterUseCase = DeleteFavouriteCharacterUseCase(repository),
            getAllCharactersUseCase = GetAllCharactersUseCase(repository),
            getCharacterByIdUseCase = GetCharacterByIdUseCase(repository),
            getFavouriteCharacterByIdUseCase = GetFavouriteCharacterByIdUseCase(repository),
            getFavouriteCharacterUseCase = GetFavouriteCharactersUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideEpisodeUseCases(repository: EpisodeRepository): EpisodeUseCases {
        return EpisodeUseCases(
            addFavouriteEpisodeUseCase = AddFavouriteEpisodeUseCase(repository),
            deleteFavouriteEpisodeUseCase = DeleteFavouriteEpisodeUseCase(repository),
            getAllEpisodesUseCase = GetAllEpisodesUseCase(repository),
            getEpisodeByIdUseCase = GetEpisodeByIdUseCase(repository),
            getAllFavouriteEpisodeUseCase = GetAllFavouriteEpisodeUseCase(repository),
            getFavouriteEpisodeByIdUseCase = GetFavouriteEpisodeByIdUseCase(repository)
        )
    }

    @ViewModelScoped
    @Provides
    fun provideLocationUseCases(repository: LocationRepository): LocationUseCases {
        return LocationUseCases(
            addFavouriteLocationUseCase = AddFavouriteLocationUseCase(repository),
            deleteFavouriteLocationUseCase = DeleteFavouriteLocationUseCase(repository),
            getAllLocationsUseCase = GetAllLocationsUseCase(repository),
            getAllFavouriteLocationsUseCase = GetAllFavouriteLocationsUseCase(repository),
            getLocationByIdUseCase = GetLocationByIdUseCase(repository),
            getFavouriteLocationByIdUseCase = GetFavouriteLocationByIdUseCase(repository)
        )
    }
}