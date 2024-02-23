package com.germanhernandez.rickmortycollection.core.util

sealed class UiEvent {
    data object Success: UiEvent()
    data object NavigateUp: UiEvent()
    data class ShowSnackBar(val message: UiText): UiEvent()
}