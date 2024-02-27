package com.germanhernandez.rickmortycollection.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.presentation.characters.components.CharactersList
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar
import com.germanhernandez.rickmortycollection.presentation.search.components.SearchTextField

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    snackBarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    onCharacterItemClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
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

                is UiEvent.Navigate -> onCharacterItemClick(event.arg.orEmpty())

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            NavTopBar(
                title = stringResource(id = Route.Search.title),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            SearchTextField(
                text = state.query,
                onValueChange = {
                    viewModel.onEvent(SearchEvent.OnQueryChange(it))
                },
                shouldShowHint = state.isHintVisible,
                onSearch = {
                    keyboardController?.hide()
                    viewModel.onEvent(SearchEvent.OnSearch)
                },
                onFocusChanged = {
                    viewModel.onEvent(SearchEvent.OnSearchFocusChanged(it.isFocused))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SearchBody(
                searchResults = state.searchResults,
                onCharacterClick = {
                    viewModel.onEvent(SearchEvent.OnCharacterClick(it))
                },
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when {
                    state.isSearching -> CircularProgressIndicator()
                    state.searchResults.isEmpty() -> {
                        Text(
                            text = stringResource(id = R.string.no_results),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBody(
    modifier: Modifier = Modifier,
    searchResults: List<Character>,
    onCharacterClick: (Int) -> Unit
) {
    CharactersList(
        characters = searchResults,
        modifier = modifier,
        onCharacterClick = onCharacterClick
    )
}