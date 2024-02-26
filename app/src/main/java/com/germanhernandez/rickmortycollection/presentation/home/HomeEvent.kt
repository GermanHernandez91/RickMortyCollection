package com.germanhernandez.rickmortycollection.presentation.home

sealed class HomeEvent {
    data object OnInitialize : HomeEvent()
    data class OnCharacterClick(val id: Int) : HomeEvent()
}