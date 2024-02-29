package com.germanhernandez.rickmortycollection.presentation.location_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.Constants
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.core.util.UiText
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import com.germanhernandez.rickmortycollection.domain.use_case.FilterCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val characterUseCases: CharacterUseCases,
    private val filterCharacterUseCase: FilterCharacterUseCase
) : ViewModel() {
    private val locationName: String =
        checkNotNull(savedStateHandle[Route.LOCATION_DETAIL_NAME_ARGUMENT])

    var state by mutableStateOf(LocationDetailState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    var uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(LocationDetailEvent.OnInitialize)
    }

    fun onEvent(event: LocationDetailEvent) {
        when (event) {
            is LocationDetailEvent.OnInitialize -> {
                loadCharactersFromLocationName()
            }
        }
    }

    private fun loadCharactersFromLocationName() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            characterUseCases
                .getAllCharactersUseCase(
                    Constants.DEFAULT_CHARACTER_PAGE,
                    null,
                    null,
                    null,
                    null
                )
                .onSuccess { characters ->
                    state = state.copy(
                        isLoading = false,
                        characters = filterCharacterUseCase(locationName, characters),
                        locationName = locationName
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