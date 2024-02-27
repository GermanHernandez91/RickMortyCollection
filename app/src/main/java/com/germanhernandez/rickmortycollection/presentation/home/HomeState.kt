package com.germanhernandez.rickmortycollection.presentation.home

import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.model.Location

data class HomeState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val locations: List<Location> = emptyList()
)