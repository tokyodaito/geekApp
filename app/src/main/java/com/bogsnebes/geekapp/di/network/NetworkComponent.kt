package com.bogsnebes.geekapp.di.network

import android.content.Context
import com.bogsnebes.geekapp.model.database.AppDatabase
import com.bogsnebes.geekapp.model.impl.FactsImpl
import com.bogsnebes.geekapp.ui.elements.BottomNavMenu
import com.bogsnebes.geekapp.ui.screens.main_screen.MainScreen
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun getRetrofit(): Retrofit
}