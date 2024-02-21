package com.germanhernandez.rickmortycollection.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.germanhernandez.rickmortycollection.R

sealed class RouteBottom(@StringRes val title: Int, val icon: ImageVector, val name: String) {
    data object Home :
        RouteBottom(title = R.string.nav_bottom_home, Icons.Default.Home, name = Route.Home.name)
}