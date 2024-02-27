package com.germanhernandez.rickmortycollection.presentation.search

sealed class SearchEvent {
    data class OnQueryChange(val query: String) : SearchEvent()
    data object OnSearch : SearchEvent()
    data class OnSearchFocusChanged(val isFocused: Boolean) : SearchEvent()
}