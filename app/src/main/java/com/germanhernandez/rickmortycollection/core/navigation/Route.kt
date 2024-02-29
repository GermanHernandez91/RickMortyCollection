package com.germanhernandez.rickmortycollection.core.navigation

import androidx.annotation.StringRes
import com.germanhernandez.rickmortycollection.R

sealed class Route(val name: String, @StringRes val title: Int) {
    data object Home : Route(name = "home", title = R.string.app_name)
    data object Characters : Route(name = "characters", title = R.string.characters)
    data object CharacterDetail :
        Route(
            name = "character/{id}",
            title = R.string.character_detail
        ) {
        fun withArgs(id: String): String {
            return this.name.replace("{$CHARACTER_DETAIL_ID_ARGUMENT}", newValue = id)
        }
    }

    data object LocationDetail :
        Route(
            name = "location/{locationName}",
            title = R.string.location_detail
        ) {
        fun withArgs(locationName: String): String {
            return this.name.replace("{$LOCATION_DETAIL_NAME_ARGUMENT}", newValue = locationName)
        }
    }

    data object Favourites : Route(name = "favourites", title = R.string.nav_bottom_favourites)

    companion object {
        const val CHARACTER_DETAIL_ID_ARGUMENT = "id"
        const val LOCATION_DETAIL_NAME_ARGUMENT = "locationName"
    }
}