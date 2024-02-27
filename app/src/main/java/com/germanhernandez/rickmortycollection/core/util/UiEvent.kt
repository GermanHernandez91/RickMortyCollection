package com.germanhernandez.rickmortycollection.core.util

sealed class UiEvent {
    data object Success : UiEvent()
    data object NavigateUp : UiEvent()
    data class Navigate(val arg: String? = null) : UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
}