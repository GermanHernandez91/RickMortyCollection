package com.germanhernandez.rickmortycollection.data.remote.dto

data class LocationDto(
    val id: Int,
    val name: String,
    val type: String?,
    val dimension: String?,
    val residents: List<String>?
)