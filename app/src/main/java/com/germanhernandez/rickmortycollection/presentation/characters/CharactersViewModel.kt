package com.germanhernandez.rickmortycollection.presentation.characters

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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
) : ViewModel() {

    var state by mutableStateOf(CharactersState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(CharactersEvent.OnInitialize)
    }

    fun onEvent(event: CharactersEvent) {
        when (event) {
            is CharactersEvent.OnInitialize -> {
                loadCharacters()
            }

            is CharactersEvent.OnCharacterClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateUp(event.id))
                }
            }
        }
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            characterUseCases
                .getAllCharactersUseCase(
                    page = state.currentPage,
                    name = null,
                    status = null,
                    type = null,
                    species = null
                )
                .onSuccess { characters ->
                    state = state.copy(
                        isLoading = true,
                        characters = characters
                    )
                }
                .onFailure {
                    state = state.copy(isLoading = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            message = UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }
    }
}