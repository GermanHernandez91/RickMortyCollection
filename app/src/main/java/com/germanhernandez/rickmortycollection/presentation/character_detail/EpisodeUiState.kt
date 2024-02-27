package com.germanhernandez.rickmortycollection.presentation.character_detail

data class EpisodeUiState(
    val isExpanded: Boolean = false,
    val episodes: List<String> = emptyList()
)