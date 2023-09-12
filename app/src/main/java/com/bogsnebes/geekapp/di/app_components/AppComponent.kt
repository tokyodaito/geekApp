package com.bogsnebes.geekapp.di.app_components

import android.content.Context
import com.bogsnebes.geekapp.ui.elements.bottom_navigation_menu.BottomNavMenu
import com.bogsnebes.geekapp.ui.screens.favorites.FavoritesScreen
import com.bogsnebes.geekapp.ui.screens.main.MainScreen
import com.bogsnebes.geekapp.ui.screens.settings.SettingsScreen
import com.yariksoffice.lingver.Lingver
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocaleModule::class])
interface AppComponent {
    fun getMainScreen(): MainScreen

    fun getBottomNavMenu(): BottomNavMenu

    fun getApplicationContext(): Context

    fun getLingver(): Lingver

    fun getFavoriteScreen(): FavoritesScreen

    fun getSettingsScreen(): SettingsScreen
}