package com.germanhernandez.rickmortycollection.core.util

sealed class UiEvent {
    data object Success : UiEvent()
    data class NavigateUp(val arg: Any? = null) : UiEvent()
    data class ShowSnackBar(val message: UiText) : UiEvent()
}