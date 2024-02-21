package com.germanhernandez.rickmortycollection.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.presentation.navigation.NavBottomBar
import com.germanhernandez.rickmortycollection.presentation.navigation.NavTopBar

@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            NavTopBar(
                title = stringResource(id = Route.Home.title),
                canNavigateBack = false
            )
        },
        bottomBar = {
            NavBottomBar(navController = navController)
        }
    ) {
        HomeBody(modifier = Modifier.padding(it))
    }
}

@Composable
fun HomeBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Home Screen")
    }
}