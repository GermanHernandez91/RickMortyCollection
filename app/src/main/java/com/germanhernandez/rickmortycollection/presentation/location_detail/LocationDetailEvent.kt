package com.germanhernandez.rickmortycollection.presentation.location_detail

sealed class LocationDetailEvent {
    data object OnInitialize : LocationDetailEvent()
}