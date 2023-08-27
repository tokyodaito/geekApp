package com.bogsnebes.geekapp.di.app_components

import android.content.Context
import com.bogsnebes.geekapp.ui.elements.BottomNavMenu
import com.bogsnebes.geekapp.ui.screens.main_screen.MainScreen
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getMainScreen(): MainScreen

    fun getBottomNavMenu(): BottomNavMenu

    fun getApplicationContext(): Context
}