package com.germanhernandez.rickmortycollection.domain.model

data class Episode(
    val id: Int? = null,
    val name: String,
    val airDate: String? = null,
    val episode: String? = null,
    val characters: List<String>? = emptyList()
)