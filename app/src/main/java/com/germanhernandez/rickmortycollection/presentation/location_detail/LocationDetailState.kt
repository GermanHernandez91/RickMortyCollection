package com.germanhernandez.rickmortycollection.presentation.location_detail

import com.germanhernandez.rickmortycollection.domain.model.Character

data class LocationDetailState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList()
)
