package com.bogsnebes.geekapp.ui.elements

import com.bogsnebes.geekapp.R

sealed class BottomNavItem(var title: String, var icon: Int, var screenRoute: String) {
    data object Settings : BottomNavItem("Settings", R.drawable.baseline_settings_24, "settings")
    data object Main : BottomNavItem("Main", R.drawable.baseline_home_24, "main")
    data object Favorites : BottomNavItem("Favorites", R.drawable.baseline_favorite_24, "favorites")
}