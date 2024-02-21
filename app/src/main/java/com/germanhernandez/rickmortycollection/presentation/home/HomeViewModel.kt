package com.germanhernandez.rickmortycollection.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.germanhernandez.rickmortycollection.domain.use_case.CharacterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val characterUseCases: CharacterUseCases
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            state = state.copy(isLoading = false, characters = emptyList())

            characterUseCases
                .getAllCharactersUseCase(
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

                }
        }
    }
}