package com.germanhernandez.rickmortycollection.presentation.characters

sealed class CharactersEvent {
    data object OnInitialize : CharactersEvent()
    data class OnQueryChange(val query: String) : CharactersEvent()
    data object OnSearch : CharactersEvent()
    data class OnSearchActiveChanged(val isActive: Boolean) : CharactersEvent()
}