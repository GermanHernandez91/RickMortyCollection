package com.germanhernandez.rickmortycollection.presentation

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.presentation.search.SearchEvent
import com.germanhernandez.rickmortycollection.presentation.search.SearchViewModel
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchViewModelTest {

    @get:Rule
    var testDispatcher = TestDispatcherRule()

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = FakeCharacterRepositoryImpl()
        searchViewModel = SearchViewModel(
            characterUseCases = CharacterUseCases(
                addFavouriteCharacterUseCase = mockk(relaxed = true),
                deleteFavouriteCharacterUseCase = mockk(relaxed = true),
                getAllCharactersUseCase = GetAllCharactersUseCase(repository = repository),
                getCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterByIdUseCase = mockk(relaxed = true),
                getFavouriteCharacterUseCase = mockk(relaxed = true)
            )
        )
    }

    @Test
    fun `SearchEvent, OnQueryChange, returns query value`() = runBlocking {
        val searchQuery = "Rick"

        searchViewModel.onEvent(SearchEvent.OnQueryChange(searchQuery))

        assertThat(searchViewModel.state.query).isEqualTo(searchQuery)
    }

    @Test
    fun `Perform search, valid character name, returns results`() = runBlocking {
        val searchQuery = "Rick"

        searchViewModel.onEvent(SearchEvent.OnQueryChange(searchQuery))
        searchViewModel.onEvent(SearchEvent.OnSearch)

        assertThat(searchViewModel.state.searchResults).isEqualTo(characters)
    }

    @Test
    fun `SearchEvent, OnSearchFocusChanged, turns isHintVariable as false`() = runBlocking {
        searchViewModel.onEvent(SearchEvent.OnSearchFocusChanged(true))

        assertThat(searchViewModel.state.isHintVisible).isFalse()
    }

    @Test
    fun `SearchEvent, OnSearchFocusChanged, turns isHintVariable as true`() = runBlocking {
        searchViewModel.onEvent(SearchEvent.OnSearchFocusChanged(false))

        assertThat(searchViewModel.state.isHintVisible).isTrue()
    }
}