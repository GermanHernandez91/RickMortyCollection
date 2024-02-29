package com.germanhernandez.rickmortycollection.presentation.location_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.presentation.characters.components.CharactersItem
import com.germanhernandez.rickmortycollection.presentation.components.EmptyScreen
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun LocationDetailScreen(
    snackBarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    onCharacterItemClick: (String) -> Unit,
    viewModel: LocationDetailViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(key1 = context) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            NavTopBar(
                title = stringResource(id = Route.LocationDetail.title, state.locationName),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) {
        LocationDetailBody(
            state = state,
            modifier = Modifier.padding(it),
            onCharacterItemClick = { id ->
                onCharacterItemClick(id.toString())
            }
        )

        EmptyScreen(isLoading = state.isLoading, data = state.characters)
    }
}

@Composable
fun LocationDetailBody(
    modifier: Modifier = Modifier,
    state: LocationDetailState,
    onCharacterItemClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.characters),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(state.characters) { character ->
            CharactersItem(character = character, onCharacterClick = onCharacterItemClick)
        }
    }
}