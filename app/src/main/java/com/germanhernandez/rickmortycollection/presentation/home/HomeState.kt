package com.germanhernandez.rickmortycollection.presentation.home

import com.germanhernandez.rickmortycollection.domain.model.Character

data class HomeState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList()
)