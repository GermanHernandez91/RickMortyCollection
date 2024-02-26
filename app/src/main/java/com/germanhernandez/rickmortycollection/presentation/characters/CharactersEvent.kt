package com.germanhernandez.rickmortycollection.presentation.characters

sealed class CharactersEvent {
    data object OnInitialize : CharactersEvent()
}