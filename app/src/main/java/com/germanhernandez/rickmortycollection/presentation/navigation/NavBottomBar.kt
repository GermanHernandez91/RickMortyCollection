package com.germanhernandez.rickmortycollection.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.germanhernandez.rickmortycollection.core.navigation.RouteBottom

@Composable
fun NavBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val navBottomItems = listOf(
        RouteBottom.Home,
        RouteBottom.Favourites
    )

    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navBottomItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.name,
                onClick = {
                    navController.navigate(item.name) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Image(
                        imageVector = item.icon,
                        contentDescription = item.name
                    )
                },
                label = {
                    Text(text = stringResource(id = item.title))
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}