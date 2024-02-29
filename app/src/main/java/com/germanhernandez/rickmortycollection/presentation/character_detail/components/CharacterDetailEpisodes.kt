package com.germanhernandez.rickmortycollection.presentation.character_detail.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.presentation.character_detail.EpisodeUiState
import com.germanhernandez.rickmortycollection.ui.theme.RickMortyCollectionTheme

@Composable
fun CharacterDetailEpisodes(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    episodeUiState: EpisodeUiState,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Episodes",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Icon(
                        imageVector = if (episodeUiState.isExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (episodeUiState.isExpanded) {
                            stringResource(id = R.string.collapse)
                        } else stringResource(id = R.string.extend)
                    )
                }
            }
        }
        AnimatedVisibility(visible = episodeUiState.isExpanded) {
            content()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCharacterDetailEpisodes() {
    RickMortyCollectionTheme {
        CharacterDetailEpisodes(
            onClick = { },
            episodeUiState = EpisodeUiState(
                isExpanded = false,
                episodes = listOf("Episode 1", "Episode 2", "Episode 3")
            ),
            content = {}
        )
    }
}