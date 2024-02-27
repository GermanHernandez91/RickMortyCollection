package com.germanhernandez.rickmortycollection.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.germanhernandez.rickmortycollection.domain.model.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationListItem(
    modifier: Modifier = Modifier,
    location: Location,
    onLocationClick: (String) -> Unit
) {
    Card(
        onClick = { onLocationClick(location.name) },
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleMedium
            )

            location.dimension?.let {
                Text(
                    text = location.dimension,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}