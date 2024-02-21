package com.germanhernandez.rickmortycollection.domain.model

data class Character(
    val id: Int? = null,
    val name: String,
    val status: String,
    val species: String,
    val type: String? = null,
    val gender: String,
    val origin: Location? = null,
    val location: Location? = null,
    val imageUrl: String,
    val episode: List<String>? = emptyList()
)
