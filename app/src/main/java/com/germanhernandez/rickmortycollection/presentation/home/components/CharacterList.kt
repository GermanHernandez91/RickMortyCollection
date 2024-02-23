package com.germanhernandez.rickmortycollection.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.germanhernandez.rickmortycollection.domain.model.Character

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    characters: List<Character> = emptyList()
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Characters",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        items(characters) { item ->
            CharacterListItem(character = item)
        }
    }
}