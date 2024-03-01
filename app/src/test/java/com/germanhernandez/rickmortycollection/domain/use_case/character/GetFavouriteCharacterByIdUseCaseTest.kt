package com.germanhernandez.rickmortycollection.domain.use_case.character

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetFavouriteCharacterByIdUseCaseTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var getFavouriteCharacterByIdUseCase: GetFavouriteCharacterByIdUseCase
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = FakeCharacterRepositoryImpl()
        getFavouriteCharacterByIdUseCase = GetFavouriteCharacterByIdUseCase(repository = repository)
    }

    @Test
    fun `Invoke, valid response, returns results`() = runBlocking {
        val id = characters.first().id ?: 0

        assertThat(
            getFavouriteCharacterByIdUseCase(id).first()
        ).isEqualTo(characters.first())
    }
}