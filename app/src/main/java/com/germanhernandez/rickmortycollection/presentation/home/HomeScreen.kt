package com.germanhernandez.rickmortycollection.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.domain.model.Location
import com.germanhernandez.rickmortycollection.presentation.components.EmptyScreen
import com.germanhernandez.rickmortycollection.presentation.home.components.CharacterList
import com.germanhernandez.rickmortycollection.presentation.home.components.CharacterListHeader
import com.germanhernandez.rickmortycollection.presentation.home.components.LocationListItem
import com.germanhernandez.rickmortycollection.presentation.navigation.NavBottomBar
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun HomeScreen(
    navController: NavController,
    snackBarHostState: SnackbarHostState,
    onCharactersClick: () -> Unit,
    onCharacterItemClick: (String) -> Unit,
    onLocationClick: (String) -> Unit,
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
    ) {
        Column(modifier = Modifier.padding(it)) {
            HomeBody(
                characters = state.characters,
                onCharactersClick = onCharactersClick,
                locations = state.locations,
                onCharacterItemClick = { id ->
                    onCharacterItemClick(id.toString())
                },
                onLocationClick = { id ->
                    onLocationClick(id)
                }
            )
        }
        EmptyScreen(isLoading = state.isLoading, data = state.characters + state.locations)
    }
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier,
    characters: List<Character> = emptyList(),
    locations: List<Location> = emptyList(),
    onCharactersClick: () -> Unit,
    onCharacterItemClick: (Int) -> Unit,
    onLocationClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        item {
            CharacterListHeader(onClickEvent = onCharactersClick)
            Spacer(modifier = Modifier.height(8.dp))
            CharacterList(
                modifier = modifier,
                characters = characters,
                onCharacterItemClick = onCharacterItemClick
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.locations),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        items(locations) { location ->
            LocationListItem(
                location = location,
                onLocationClick = onLocationClick
            )
        }
    }
}