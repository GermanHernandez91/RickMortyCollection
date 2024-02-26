package com.germanhernandez.rickmortycollection.presentation.characters

import com.germanhernandez.rickmortycollection.domain.model.Character

data class CharactersState(
    val isLoading: Boolean = false,
    val currentPage: Int = 1,
    val characters: List<Character> = emptyList()
)