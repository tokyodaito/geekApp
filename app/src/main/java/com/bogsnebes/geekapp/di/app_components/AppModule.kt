package com.bogsnebes.geekapp.di.app_components

import android.content.Context
import com.bogsnebes.geekapp.ui.elements.bottom_navigation_menu.BottomNavMenu
import com.bogsnebes.geekapp.ui.screens.main.MainScreen
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
}