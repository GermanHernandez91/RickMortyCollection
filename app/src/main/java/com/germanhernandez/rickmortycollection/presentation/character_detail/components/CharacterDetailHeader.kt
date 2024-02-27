package com.germanhernandez.rickmortycollection.presentation.character_detail.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.germanhernandez.rickmortycollection.domain.model.Character

@Composable
fun CharacterDetailHeader(
    modifier: Modifier = Modifier,
    character: Character
) {
    SubcomposeAsyncImage(
        model = character.imageUrl,
        contentDescription = character.name,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(12.dp)),
        loading = {
            CircularProgressIndicator()
        }
    )
}