package com.germanhernandez.rickmortycollection.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.Constants
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.core.util.UiText
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(HomeEvent.OnInitialize)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnInitialize -> {
                loadCharacters()
            }

            is HomeEvent.OnCharacterClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp(event.id))
                }
            }
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            state = state.copy(isLoading = false, characters = emptyList())

            characterUseCases
                .getAllCharactersUseCase(
                    page = Constants.DEFAULT_CHARACTER_PAGE,
                    name = null,
                    type = null,
                    species = null,
                    status = null,
                )
                .onSuccess { characters ->
                    state = state.copy(
                        isLoading = false,
                        characters = characters
                    )
                }
                .onFailure {
                    state = state.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }
    }
}