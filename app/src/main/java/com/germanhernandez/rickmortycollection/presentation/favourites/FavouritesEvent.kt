package com.germanhernandez.rickmortycollection.presentation.favourites

sealed class FavouritesEvent {
    data object OnInitialize : FavouritesEvent()
}