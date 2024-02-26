package com.germanhernandez.rickmortycollection.presentation.character_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun CharacterDetailScreen(
    snackBarHostState: SnackbarHostState,
    onNavigateUp: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
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
                title = stringResource(id = Route.CharacterDetail.title),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) {
        CharacterDetailBody(character = state.character, modifier = Modifier.padding(it))
    }
}

@Composable
fun CharacterDetailBody(
    modifier: Modifier = Modifier,
    character: Character?
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = character?.name ?: "")
    }
}