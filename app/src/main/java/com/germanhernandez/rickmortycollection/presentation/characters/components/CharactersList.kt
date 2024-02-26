package com.germanhernandez.rickmortycollection.presentation.characters.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.germanhernandez.rickmortycollection.domain.model.Character

@Composable
fun CharactersList(
    modifier: Modifier = Modifier,
    characters: List<Character>
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(characters) { item ->
            CharactersItem(character = item)
        }
    }
}