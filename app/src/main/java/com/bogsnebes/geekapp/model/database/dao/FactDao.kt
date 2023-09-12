package com.bogsnebes.geekapp.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bogsnebes.geekapp.model.database.dto.FactDto

@Dao
interface FactDao {
    @Query("SELECT * FROM fact")
    fun getAll(): List<FactDto>

    @Query("SELECT * FROM fact WHERE id LIKE :id")
    fun findById(id: String): FactDto?

    @Insert
    fun insertAll(vararg facts: FactDto)

    @Insert
    fun insert(fact: FactDto)

    @Delete
    fun delete(fact: FactDto)
}