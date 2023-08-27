package com.bogsnebes.geekapp.di

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.model.impl.FactsImpl
import com.bogsnebes.geekapp.ui.MainActivity
import com.bogsnebes.geekapp.ui.elements.BottomNavMenu
import com.bogsnebes.geekapp.ui.screens.main_screen.MainScreen
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getMainScreen(): MainScreen

    fun getRetrofit(): Retrofit

    fun getFactsImpl(): FactsImpl

    fun getBottomNavMenu(): BottomNavMenu
    fun getApplication(): Application

    fun getMainActivity(): MainActivity
}