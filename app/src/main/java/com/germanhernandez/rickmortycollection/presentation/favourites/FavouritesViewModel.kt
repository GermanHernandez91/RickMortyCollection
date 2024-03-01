package com.germanhernandez.rickmortycollection.presentation.favourites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.core.util.UiText
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val charactersUseCases: CharacterUseCases
) : ViewModel() {

    var state by mutableStateOf(FavouritesState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(FavouritesEvent.OnInitialize)
    }

    fun onEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.OnInitialize -> {
                loadFavourites()
            }
        }
    }

    private fun loadFavourites() {
        viewModelScope.launch {
            charactersUseCases
                .getFavouriteCharacterUseCase()
                .catch {
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            message = UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
                .collect { characters ->
                    state = state.copy(favourites = characters)
                }
        }
    }
}