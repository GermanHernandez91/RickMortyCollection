package com.germanhernandez.rickmortycollection.presentation.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.germanhernandez.rickmortycollection.core.navigation.Route
import com.germanhernandez.rickmortycollection.presentation.character_detail.CharacterDetailScreen
import com.germanhernandez.rickmortycollection.presentation.characters.CharactersScreen
import com.germanhernandez.rickmortycollection.presentation.favourites.FavouritesScreen
import com.germanhernandez.rickmortycollection.presentation.home.HomeScreen
import com.germanhernandez.rickmortycollection.presentation.location_detail.LocationDetailScreen

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
                },
                onCharacterItemClick = { arg ->
                    navHostController.navigate(Route.CharacterDetail.withArgs(arg))
                },
                onLocationClick = { arg ->
                    navHostController.navigate(Route.LocationDetail.withArgs(arg))
                }
            )
        }

        composable(route = Route.Characters.name) {
            CharactersScreen(
                snackBarHostState = snackBarHostState,
                onNavigateUp = {
                    navHostController.navigateUp()
                },
                onCharacterItemClick = { arg ->
                    navHostController.navigate(Route.CharacterDetail.withArgs(arg))
                }
            )
        }

        composable(
            route = Route.CharacterDetail.name,
            arguments = listOf(navArgument(Route.CHARACTER_DETAIL_ID_ARGUMENT) {
                type = NavType.IntType
            })
        ) {
            CharacterDetailScreen(
                snackBarHostState = snackBarHostState,
                onNavigateUp = { navHostController.navigateUp() }
            )
        }

        composable(
            route = Route.LocationDetail.name,
            arguments = listOf(navArgument(Route.LOCATION_DETAIL_NAME_ARGUMENT) {
                type = NavType.StringType
            })
        ) {
            LocationDetailScreen(
                snackBarHostState = snackBarHostState,
                onNavigateUp = { navHostController.navigateUp() },
                onCharacterItemClick = { arg ->
                    navHostController.navigate(Route.CharacterDetail.withArgs(arg))
                }
            )
        }

        composable(route = Route.Favourites.name) {
            FavouritesScreen(
                snackBarHostState = snackBarHostState,
                navController = navHostController,
                onCharacterItemClick = { arg ->
                    navHostController.navigate(Route.CharacterDetail.withArgs(arg))
                }
            )
        }
    }
}