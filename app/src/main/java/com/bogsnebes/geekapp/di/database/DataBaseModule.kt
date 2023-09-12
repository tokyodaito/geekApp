package com.bogsnebes.geekapp.di.database

import android.content.Context
import androidx.room.Room
import com.bogsnebes.geekapp.model.database.AppDatabase
import com.bogsnebes.geekapp.model.impl.FactsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private val applicationContext: Context) {
    @Provides
    @Singleton
    fun getDataBase(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun getFactsImpl(): FactsImpl {
        return FactsImpl()
    }
}