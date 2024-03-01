package com.germanhernandez.rickmortycollection.presentation.character_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.core.util.UiEvent
import com.germanhernandez.rickmortycollection.domain.model.Character
import com.germanhernandez.rickmortycollection.presentation.character_detail.components.CharacterDetailEpisodes
import com.germanhernandez.rickmortycollection.presentation.character_detail.components.CharacterDetailHeader
import com.germanhernandez.rickmortycollection.presentation.character_detail.components.CharacterDetailInfo
import com.germanhernandez.rickmortycollection.presentation.character_detail.components.CharacterDetailName
import com.germanhernandez.rickmortycollection.presentation.components.EmptyScreen
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
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(CharacterDetailEvent.OnFavouriteClick)
            }) {
                Icon(
                    imageVector = if (state.isFavourite) {
                        Icons.Default.Favorite
                    } else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favourite_icon)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            item {
                CharacterDetailBody(
                    character = state.character,
                    modifier = Modifier.padding(16.dp),
                    episodeUiState = state.episodeUiState,
                    onClick = {
                        viewModel.onEvent(CharacterDetailEvent.OnToggleEpisodeClick)
                    }
                )
            }
        }

        EmptyScreen(isLoading = state.isLoading, data = listOf(""))
    }
}

@Composable
fun CharacterDetailBody(
    modifier: Modifier = Modifier,
    character: Character?,
    episodeUiState: EpisodeUiState,
    onClick: () -> Unit
) {
    Column(modifier = modifier) {
        character?.let {
            CharacterDetailName(character = character)
            Spacer(modifier = Modifier.height(12.dp))
            CharacterDetailHeader(character = character)
            Spacer(modifier = Modifier.height(12.dp))
            CharacterDetailInfo(character = character)
            Spacer(modifier = Modifier.height(12.dp))
            CharacterDetailEpisodes(
                onClick = onClick,
                episodeUiState = episodeUiState,
                content = {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            episodeUiState.episodes.forEachIndexed { index, episode ->
                                Text(
                                    text = stringResource(
                                        id = R.string.character_detail_info_episode,
                                        episode
                                    ),
                                    modifier = Modifier.padding(10.dp)
                                )

                                if (episodeUiState.episodes.lastIndex != index) {
                                    Divider()
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}