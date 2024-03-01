package com.germanhernandez.rickmortycollection.presentation

import androidx.lifecycle.SavedStateHandle
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.FilterEpisodeUseCase
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetCharacterByIdUseCase
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.presentation.character_detail.CharacterDetailEvent
import com.germanhernandez.rickmortycollection.presentation.character_detail.CharacterDetailViewModel
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var characterDetailViewModel: CharacterDetailViewModel
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        val testId = 1
        val savedState = SavedStateHandle(mapOf(Route.CHARACTER_DETAIL_ID_ARGUMENT to testId))

        repository = FakeCharacterRepositoryImpl()
        characterDetailViewModel = CharacterDetailViewModel(
            savedStateHandle = savedState,
            charactersUseCases = CharacterUseCases(
                addFavouriteCharacterUseCase = mockk(relaxed = true),
                deleteFavouriteCharacterUseCase = mockk(relaxed = true),
                getAllCharactersUseCase = mockk(relaxed = true),
                getCharacterByIdUseCase = GetCharacterByIdUseCase(repository),
                getFavouriteCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterUseCase = mockk(relaxed = true)
            ),
            filterEpisodeUseCase = FilterEpisodeUseCase()
        )
    }

    @Test
    fun `OnInitialize, valid response, returns character data`() = runBlocking {
        assertThat(characterDetailViewModel.state.character).isEqualTo(characters.first())
    }

    @Test
    fun `Load Characters, valid response, returns formatted episodes`() = runBlocking {
        assertThat(characterDetailViewModel.state.episodeUiState.episodes).isEqualTo(
            listOf(
                "1",
                "2",
                "3"
            )
        )
    }

    @Test
    fun `OnToggleEpisodeClick, onEvent called, set isExpanded correctly`() = runBlocking {
        assertThat(characterDetailViewModel.state.episodeUiState.isExpanded).isFalse()
        characterDetailViewModel.onEvent(CharacterDetailEvent.OnToggleEpisodeClick)
        assertThat(characterDetailViewModel.state.episodeUiState.isExpanded).isTrue()
        characterDetailViewModel.onEvent(CharacterDetailEvent.OnToggleEpisodeClick)
        assertThat(characterDetailViewModel.state.episodeUiState.isExpanded).isFalse()
    }

    @Test
    fun `OnFavouriteClick, favourite clicked, add favourite`() = runBlocking {
        characterDetailViewModel.onEvent(CharacterDetailEvent.OnFavouriteClick)
        assertThat(characterDetailViewModel.state.isFavourite).isTrue()
    }

    @Test
    fun `OnInitialize, loadFavourite, return results`() = runBlocking {
        characterDetailViewModel.onEvent(CharacterDetailEvent.OnFavouriteClick)
        characterDetailViewModel.onEvent(CharacterDetailEvent.OnInitialize)

        assertThat(characterDetailViewModel.state.isFavourite).isTrue()
    }

    @Test
    fun `OnInitialize, loadFavourite failed, set isFavourite`() = runBlocking {
        characterDetailViewModel.onEvent(CharacterDetailEvent.OnInitialize)

        assertThat(characterDetailViewModel.state.isFavourite).isFalse()
    }
}