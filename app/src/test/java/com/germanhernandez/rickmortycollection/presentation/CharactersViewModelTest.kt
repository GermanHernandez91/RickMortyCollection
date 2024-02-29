package com.germanhernandez.rickmortycollection.presentation

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.presentation.characters.CharactersEvent
import com.germanhernandez.rickmortycollection.presentation.characters.CharactersViewModel
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharactersViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var viewModel: CharactersViewModel
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = FakeCharacterRepositoryImpl()
        viewModel = CharactersViewModel(
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
    fun `OnInitialize, loadCharacters, return results`() = runBlocking {
        assertThat(viewModel.state.characters).isNotEmpty()
        assertThat(viewModel.state.characters).isEqualTo(characters)
    }

    @Test
    fun `OnQueryChange, receive new query, update state`() = runBlocking {
        val query = "Rick"
        viewModel.onEvent(CharactersEvent.OnQueryChange(query))

        assertThat(viewModel.state.query).isEqualTo(query)
    }

    @Test
    fun `OnSearchActiveChanged, isActive is true, update state`() = runBlocking {
        val query = "Rick"

        viewModel.onEvent(CharactersEvent.OnQueryChange(query))
        viewModel.onEvent(CharactersEvent.OnSearchActiveChanged(true))

        assertThat(viewModel.state.isSearching).isFalse()
        assertThat(viewModel.state.query).isEmpty()
    }

    @Test
    fun `OnSearchActiveChanged, isActive is false, update state`() = runBlocking {
        val query = "Rick"

        viewModel.onEvent(CharactersEvent.OnQueryChange(query))
        viewModel.onEvent(CharactersEvent.OnSearchActiveChanged(false))

        assertThat(viewModel.state.isSearching).isTrue()
        assertThat(viewModel.state.query).isEqualTo(query)
    }

    @Test
    fun `OnSearch, performSearch, returns results`() = runBlocking {
        val query = "Rick"

        viewModel.onEvent(CharactersEvent.OnQueryChange(query))
        viewModel.onEvent(CharactersEvent.OnSearch)

        assertThat(viewModel.state.query).isEmpty()
        assertThat(viewModel.state.isSearching).isFalse()
        assertThat(viewModel.state.searchResults).isEqualTo(characters)
    }
}