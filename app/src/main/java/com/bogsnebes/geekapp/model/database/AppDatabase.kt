package com.bogsnebes.geekapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bogsnebes.geekapp.model.database.dao.FactDao
import com.bogsnebes.geekapp.model.database.dto.FactDto

@Database(entities = [FactDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao
}