package com.germanhernandez.rickmortycollection.presentation.character_detail

import com.germanhernandez.rickmortycollection.domain.model.Character

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val episodeUiState: EpisodeUiState = EpisodeUiState()
)
