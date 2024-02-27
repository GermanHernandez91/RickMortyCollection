package com.germanhernandez.rickmortycollection.presentation.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.presentation.characters.components.CharactersList
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun CharactersScreen(
    onNavigateUp: () -> Unit,
    onSearchClick: () -> Unit,
    onCharacterItemClick: (String) -> Unit,
    viewModel: CharactersViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold(
        topBar = {
            NavTopBar(
                title = stringResource(id = Route.Characters.title),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
                actions = {
                    IconButton(onClick = onSearchClick) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                }
            )
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
}