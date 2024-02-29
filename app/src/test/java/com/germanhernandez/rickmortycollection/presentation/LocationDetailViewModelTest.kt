package com.germanhernandez.rickmortycollection.presentation

import androidx.lifecycle.SavedStateHandle
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.FilterCharacterUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.locations
import com.germanhernandez.rickmortycollection.presentation.location_detail.LocationDetailViewModel
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LocationDetailViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var viewModel: LocationDetailViewModel
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        val locationTestName = "Earth"
        val savedState =
            SavedStateHandle(mapOf(Route.LOCATION_DETAIL_NAME_ARGUMENT to locationTestName))

        repository = FakeCharacterRepositoryImpl()
        viewModel = LocationDetailViewModel(
            savedStateHandle = savedState,
            characterUseCases = CharacterUseCases(
                addFavouriteCharacterUseCase = mockk(relaxed = true),
                deleteFavouriteCharacterUseCase = mockk(relaxed = true),
                getAllCharactersUseCase = GetAllCharactersUseCase(repository),
                getCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterUseCase = mockk(relaxed = true)
            ),
            filterCharacterUseCase = FilterCharacterUseCase()
        )
    }

    @Test
    fun `OnInitialize, loadCharactersFromLocationName, return results`() = runBlocking {
        assertThat(viewModel.state.characters.first().location).isEqualTo(locations.first())
    }
}