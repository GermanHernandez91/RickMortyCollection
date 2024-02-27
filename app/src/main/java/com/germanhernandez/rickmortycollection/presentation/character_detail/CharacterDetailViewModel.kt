package com.germanhernandez.rickmortycollection.presentation.character_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.core.util.UiText
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.FilterEpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val charactersUseCases: CharacterUseCases,
    private val filterEpisodeUseCase: FilterEpisodeUseCase
) : ViewModel() {

    private val id: Int = checkNotNull(savedStateHandle[Route.CHARACTER_DETAIL_ID_ARGUMENT])

    var state by mutableStateOf(CharacterDetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(CharacterDetailEvent.OnInitialize)
    }

    fun onEvent(event: CharacterDetailEvent) {
        when (event) {
            is CharacterDetailEvent.OnInitialize -> {
                loadCharacterById()
            }

            is CharacterDetailEvent.OnToggleEpisodeClick -> {
                state =
                    state.copy(
                        episodeUiState = state.episodeUiState.copy(
                            isExpanded = !state.episodeUiState.isExpanded
                        )
                    )
            }
        }
    }

    private fun loadCharacterById() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            charactersUseCases
                .getCharacterByIdUseCase(id ?: 0)
                .onSuccess { character ->
                    state = state.copy(
                        isLoading = false,
                        character = character,
                        episodeUiState = EpisodeUiState(
                            episodes = filterEpisodeUseCase(character.episode.orEmpty())
                        )
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