package com.germanhernandez.rickmortycollection.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.presentation.home.components.CharacterList
import com.germanhernandez.rickmortycollection.presentation.home.components.CharacterListHeader
import com.germanhernandez.rickmortycollection.presentation.navigation.NavBottomBar
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun HomeScreen(
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    onCharactersClick: () -> Unit,
    onCharacterItemClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
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

                is UiEvent.Navigate -> onCharacterItemClick(event.arg.orEmpty())

                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            NavTopBar(
                title = stringResource(id = Route.Home.title),
                canNavigateBack = false
            )
        },
        bottomBar = {
            NavBottomBar(navController = navController)
        }
    ) { it ->
        Column(modifier = Modifier.padding(it)) {
            HomeBody(
                characters = state.characters,
                onCharactersClick = onCharactersClick,
                onCharacterItemClick = { id ->
                    viewModel.onEvent(HomeEvent.OnCharacterClick(id))
                }
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when {
                    state.isLoading -> CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    characters: List<Character> = emptyList(),
    onCharactersClick: () -> Unit,
    onCharacterItemClick: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CharacterListHeader(onClickEvent = onCharactersClick)
        Spacer(modifier = Modifier.height(16.dp))
        CharacterList(
            modifier = modifier,
            characters = characters,
            onCharacterItemClick = onCharacterItemClick
        )
    }
}