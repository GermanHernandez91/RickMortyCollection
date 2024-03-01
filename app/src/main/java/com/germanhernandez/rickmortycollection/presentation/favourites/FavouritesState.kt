package com.germanhernandez.rickmortycollection.presentation.favourites

import com.germanhernandez.rickmortycollection.domain.model.Character

data class FavouritesState(
    val favourites: List<Character> = emptyList()
)
