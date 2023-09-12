package com.bogsnebes.geekapp.di.app_components

import android.content.Context
import com.bogsnebes.geekapp.ui.elements.bottom_navigation_menu.BottomNavMenu
import com.bogsnebes.geekapp.ui.screens.favorites.FavoritesScreen
import com.bogsnebes.geekapp.ui.screens.main.MainScreen
import com.bogsnebes.geekapp.ui.screens.settings.SettingsScreen
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val applicationContext: Context) {
    @Provides
    fun getMainScreen() = MainScreen()

    @Provides
    fun getBottomNavMenu(): BottomNavMenu {
        return BottomNavMenu()
    }

    @Provides
    fun getApplicationContext(): Context {
        return applicationContext
    }

    @Provides
    fun getFavoriteScreen(): FavoritesScreen {
        return FavoritesScreen()
    }

    @Provides
    fun getSettingsScreen(): SettingsScreen {
        return SettingsScreen()
    }
}