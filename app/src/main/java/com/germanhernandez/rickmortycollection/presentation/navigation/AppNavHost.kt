package com.germanhernandez.rickmortycollection.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.presentation.characters.CharactersScreen
import com.germanhernandez.rickmortycollection.presentation.home.HomeScreen
import com.germanhernandez.rickmortycollection.presentation.search.SearchScreen

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
                snackBarHostState = snackBarHostState,
                onCharactersClick = {
                    navHostController.navigate(Route.Characters.name)
                }
            )
        }

        composable(route = Route.Characters.name) {
            CharactersScreen(
                onNavigateUp = {
                    navHostController.navigateUp()
                },
                onSearchClick = {
                    navHostController.navigate(Route.Search.name)
                }
            )
        }

        composable(route = Route.Search.name) {
            SearchScreen(
                snackBarHostState = snackBarHostState,
                onNavigateUp = { navHostController.navigateUp() }
            )
        }
    }
}