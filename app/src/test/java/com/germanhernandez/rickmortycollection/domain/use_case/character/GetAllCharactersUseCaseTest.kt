package com.germanhernandez.rickmortycollection.domain.use_case.character

import com.germanhernandez.rickmortycollection.domain.repository.CharacterRepository
import com.germanhernandez.rickmortycollection.fake.FakeCharacterRepositoryImpl
import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.util.TestDispatcherRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetAllCharactersUseCaseTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var getAllCharactersUseCaseTest: GetAllCharactersUseCase
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        repository = FakeCharacterRepositoryImpl()
        getAllCharactersUseCaseTest = GetAllCharactersUseCase(repository = repository)
    }

    @Test
    fun `Invoke, valid response, returns results`() = runBlocking {
        assertThat(
            getAllCharactersUseCaseTest
                .invoke(1, null, null, null, null)
        ).isEqualTo(Result.success(characters))
    }
}