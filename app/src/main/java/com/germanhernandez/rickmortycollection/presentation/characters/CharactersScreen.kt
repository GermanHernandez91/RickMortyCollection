package com.germanhernandez.rickmortycollection.presentation.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.presentation.characters.components.CharactersList
import com.germanhernandez.rickmortycollection.presentation.components.EmptyScreen

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    snackBarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    onCharacterItemClick: (String) -> Unit,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()

                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            DockedSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                query = state.query,
                onQueryChange = {
                    viewModel.onEvent(CharactersEvent.OnQueryChange(it))
                },
                onSearch = {
                    keyboardController?.hide()
                    viewModel.onEvent(CharactersEvent.OnSearch)
                },
                active = state.isSearching,
                onActiveChange = {
                    viewModel.onEvent(CharactersEvent.OnSearchActiveChanged(state.isSearching))
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.search_characters_hint))
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = stringResource(
                            id = R.string.search
                        )
                    )
                },
                leadingIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            ) {
                CharactersList(
                    characters = state.searchResults,
                    onCharacterClick = {
                        onCharacterItemClick(it.toString())
                    }
                )
            }
        }
    ) {
        CharactersBody(
            state = state,
            modifier = Modifier.padding(it),
            onCharacterClick = { id ->
                onCharacterItemClick(id.toString())
            }
        )
    }
}

@Composable
fun CharactersBody(
    modifier: Modifier = Modifier,
    state: CharactersState,
    onCharacterClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CharactersList(
            characters = state.characters,
            onCharacterClick = onCharacterClick
        )
    }

    EmptyScreen(isLoading = state.isLoading, data = state.characters)
}