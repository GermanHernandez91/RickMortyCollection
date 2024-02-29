package com.germanhernandez.rickmortycollection.presentation.characters

import com.germanhernandez.rickmortycollection.domain.model.Character

data class CharactersState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val query: String = "",
    val isSearching: Boolean = false,
    val searchResults: List<Character> = emptyList()
)