package com.germanhernandez.rickmortycollection.presentation.character_detail

sealed class CharacterDetailEvent {
    data object OnInitialize : CharacterDetailEvent()
    data object OnToggleEpisodeClick : CharacterDetailEvent()
}