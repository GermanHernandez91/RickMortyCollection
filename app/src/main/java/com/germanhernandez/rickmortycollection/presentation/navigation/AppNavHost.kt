package com.germanhernandez.rickmortycollection.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.presentation.home.HomeScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    snackBarHostState: SnackbarHostState
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Route.Home.name
    ) {
        composable(route = Route.Home.name) {
            HomeScreen(
                navController = navHostController,
                snackBarHostState = snackBarHostState
            )
        }
    }
}