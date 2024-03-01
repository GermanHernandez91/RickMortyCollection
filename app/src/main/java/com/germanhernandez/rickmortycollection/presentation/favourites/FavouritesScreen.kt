package com.germanhernandez.rickmortycollection.presentation.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.presentation.characters.components.CharactersList
import com.germanhernandez.rickmortycollection.presentation.components.EmptyScreen
import com.germanhernandez.rickmortycollection.presentation.navigation.NavBottomBar
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun FavouritesScreen(
    snackBarHostState: SnackbarHostState,
    navController: NavController,
    onCharacterItemClick: (String) -> Unit,
    viewModel: FavouritesViewModel = hiltViewModel()
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
                title = stringResource(id = Route.Favourites.title),
                canNavigateBack = false
            )
        },
        bottomBar = {
            NavBottomBar(navController = navController)
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            FavouritesBody(
                favourites = state.favourites,
                onCharacterClick = { onCharacterItemClick(it.toString()) })
        }

        EmptyScreen(isLoading = false, data = state.favourites)
    }
}

@Composable
fun FavouritesBody(
    modifier: Modifier = Modifier,
    favourites: List<Character>,
    onCharacterClick: (Int) -> Unit
) {
    CharactersList(
        modifier = modifier,
        characters = favourites,
        onCharacterClick = onCharacterClick
    )
}