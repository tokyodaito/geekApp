package com.bogsnebes.geekapp.di.database

import com.bogsnebes.geekapp.model.database.AppDatabase
import com.bogsnebes.geekapp.model.impl.FactsImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataBaseModule::class])
interface DataBaseComponent {
    fun getDatabase(): AppDatabase

    fun getFactsImpl(): FactsImpl
}