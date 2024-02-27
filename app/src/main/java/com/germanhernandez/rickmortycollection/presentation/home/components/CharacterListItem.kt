package com.germanhernandez.rickmortycollection.presentation.home.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.domain.model.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListItem(
    modifier: Modifier = Modifier,
    character: Character,
    onCharacterClick: (Int) -> Unit
) {
    Card(
        onClick = { character.id?.let { onCharacterClick(it) } },
        modifier = modifier.clip(CircleShape)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.placeholder),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape)
        )
    }
}
