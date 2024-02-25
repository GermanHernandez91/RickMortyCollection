package com.germanhernandez.rickmortycollection.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.germanhernandez.rickmortycollection.domain.model.Character

@Composable
fun CharacterList(
    modifier: Modifier = Modifier,
    characters: List<Character> = emptyList()
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(characters) { item ->
            CharacterListItem(character = item)
        }
    }
}