package com.germanhernandez.rickmortycollection.presentation.character_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.domain.model.Character

@Composable
fun CharacterDetailName(
    modifier: Modifier = Modifier,
    character: Character
) {
    Column(modifier = modifier) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Row {
            Text(
                text = stringResource(
                    id = R.string.characters_item_info,
                    character.status,
                    character.species
                ),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}