package com.germanhernandez.rickmortycollection.presentation.favourites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.germanhernandez.rickmortycollection.R
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.presentation.navigation.NavBottomBar
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun FavouritesScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            NavTopBar(
                title = stringResource(id = Route.Favourites.title),
                canNavigateBack = false
            )
        },
        bottomBar = {
            NavBottomBar(navController = navController)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            FavouritesBody()
        }
    }
}

@Composable
fun FavouritesBody(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = stringResource(id = R.string.nav_bottom_favourites))
    }
}