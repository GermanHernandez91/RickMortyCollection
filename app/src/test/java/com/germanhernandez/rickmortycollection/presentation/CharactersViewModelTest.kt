package com.germanhernandez.rickmortycollection.presentation

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.character.GetAllCharactersUseCase
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
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

    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = FakeCharacterRepositoryImpl()
        charactersViewModel = CharactersViewModel(
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
    fun `Initialize view model contains character list`() = runBlocking {
        assertThat(charactersViewModel.state.characters).isNotEmpty()
        assertThat(charactersViewModel.state.characters).isEqualTo(characters)
    }
}