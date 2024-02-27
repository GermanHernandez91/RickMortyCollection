package com.germanhernandez.rickmortycollection.presentation

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.repository.LocationRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.LocationUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.location.GetAllLocationsUseCase
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.FakeLocationRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.fake.locations
import com.germanhernandez.rickmortycollection.presentation.home.HomeViewModel
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var characterRepository: CharacterRepository
    private lateinit var locationRepository: LocationRepository

    @Before
    fun setUp() {
        locationRepository = FakeLocationRepositoryImpl()
        characterRepository = FakeCharacterRepositoryImpl()
        homeViewModel = HomeViewModel(
            characterUseCases = CharacterUseCases(
                addFavouriteCharacterUseCase = mockk(relaxed = true),
                deleteFavouriteCharacterUseCase = mockk(relaxed = true),
                getAllCharactersUseCase = GetAllCharactersUseCase(repository = characterRepository),
                getCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterUseCase = mockk(relaxed = true)
            ),
            locationUseCases = LocationUseCases(
                addFavouriteLocationUseCase = mockk(relaxed = true),
                deleteFavouriteLocationUseCase = mockk(relaxed = true),
                getAllLocationsUseCase = GetAllLocationsUseCase(repository = locationRepository),
                getLocationByIdUseCase = mockk(relaxed = true),
                getFavouriteLocationByIdUseCase = mockk(relaxed = true),
                getAllFavouriteLocationsUseCase = mockk(relaxed = true)
            )
        )
    }

    @Test
    fun `OnInitialize, loadCharacters, returns results`() = runBlocking {
        assertThat(homeViewModel.state.characters).isNotEmpty()
        assertThat(homeViewModel.state.characters).isEqualTo(characters)
    }

    @Test
    fun `OnInitialize, loadLocations, returns results`() = runBlocking {
        assertThat(homeViewModel.state.locations).isNotEmpty()
        assertThat(homeViewModel.state.locations).isEqualTo(locations)
    }
}