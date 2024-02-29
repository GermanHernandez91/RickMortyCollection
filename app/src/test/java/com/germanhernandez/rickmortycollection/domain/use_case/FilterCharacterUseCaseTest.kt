package com.germanhernandez.rickmortycollection.domain.use_case

import com.germanhernandez.rickmortycollection.fake.characters
import com.germanhernandez.rickmortycollection.fake.locations
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FilterCharacterUseCaseTest {

    private lateinit var filterCharacterUseCase: FilterCharacterUseCase

    @Before
    fun setUp() {
        filterCharacterUseCase = FilterCharacterUseCase()
    }

    @Test
    fun `Invoke, valid location name, returns results`() = runBlocking {
        val filteredCharacters =
            filterCharacterUseCase(locationName = "Earth", characters = characters)

        assertThat(filteredCharacters.first().location).isEqualTo(locations.first())
        assertThat(filteredCharacters).isNotEqualTo(characters)
    }

    @Test
    fun `Invoke, invalid location name, returns all`() = runBlocking {
        val filteredCharacters =
            filterCharacterUseCase(locationName = "", characters = characters)

        assertThat(filteredCharacters).isEqualTo(characters)
    }
}