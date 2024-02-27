package com.germanhernandez.rickmortycollection.domain.use_case

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FilterEpisodeUseCaseTest {

    private lateinit var filterEpisodeUseCase: FilterEpisodeUseCase

    @Before
    fun setUp() {
        filterEpisodeUseCase = FilterEpisodeUseCase()
    }

    @Test
    fun `Invoke, valid string list, returns formatted list`() = runBlocking {
        val episodes = listOf(
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3"
        )

        val formattedList = filterEpisodeUseCase(episodes)

        assertThat(formattedList).isEqualTo(listOf("1", "2", "3"))
    }
}