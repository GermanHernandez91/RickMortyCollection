package com.germanhernandez.rickmortycollection.presentation.characters

sealed class CharactersEvent {
    data object OnInitialize : CharactersEvent()
    data class OnCharacterClick(val id: Int) : CharactersEvent()
}