package com.bogsnebes.geekapp.ui.elements.bottom_navigation_menu

import androidx.annotation.StringRes
import com.bogsnebes.geekapp.R

sealed class BottomNavItem(@StringRes var titleResId: Int, var icon: Int, var screenRoute: String) {
    data object Settings :
        BottomNavItem(R.string.settings, R.drawable.baseline_settings_24, "settings")

    data object Main : BottomNavItem(R.string.main, R.drawable.baseline_home_24, "main")
    data object Favorites :
        BottomNavItem(R.string.favorites, R.drawable.baseline_favorite_24, "favorites")
}