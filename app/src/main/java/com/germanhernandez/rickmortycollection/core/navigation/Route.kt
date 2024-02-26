package com.germanhernandez.rickmortycollection.core.navigation

import androidx.annotation.StringRes
import com.germanhernandez.rickmortycollection.R

sealed class Route(val name: String, @StringRes val title: Int) {
    data object Home : Route(name = "home", title = R.string.app_name)
    data object Characters : Route(name = "characters", title = R.string.characters)
    data object Search : Route(name = "search", title = R.string.search_characters)
}