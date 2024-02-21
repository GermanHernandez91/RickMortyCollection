package com.germanhernandez.rickmortycollection.domain.model

data class Location(
    val id: Int? = null,
    val name: String,
    val type: String? = null,
    val dimension: String? = null,
    val residents: List<String>? = emptyList()
)