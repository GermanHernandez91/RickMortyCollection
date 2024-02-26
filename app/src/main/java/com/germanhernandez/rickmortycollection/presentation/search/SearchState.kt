package com.germanhernandez.rickmortycollection.presentation.search

import com.germanhernandez.rickmortycollection.domain.model.Character

data class SearchState(
    val query: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val searchResults: List<Character> = emptyList()
)
