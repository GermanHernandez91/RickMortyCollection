package com.germanhernandez.rickmortycollection.core.navigation

import androidx.annotation.StringRes
import com.germanhernandez.rickmortycollection.R

sealed class Route(val name: String, @StringRes val title: Int) {
    data object Home : Route(name = "home", title = R.string.app_name)
    data object Characters : Route(name = "characters", title = R.string.characters)
    data object Search : Route(name = "search", title = R.string.search_characters)
    data object CharacterDetail :
        Route(
            name = "character/{id}",
            title = R.string.character_detail
        ) {
        fun withArgs(id: String): String {
            return this.name.replace("{$CHARACTER_DETAIL_ID_ARGUMENT}", newValue = id)
        }
    }

    companion object {
        const val CHARACTER_DETAIL_ID_ARGUMENT = "id"
    }
}