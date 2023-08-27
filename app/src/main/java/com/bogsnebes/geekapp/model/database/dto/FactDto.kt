package com.bogsnebes.geekapp.model.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Fact")
data class FactDto(
    @PrimaryKey
    val id: String,
    val text: String,
    val source: String,
    val sourceUrl: String,
    val language: String,
    val permalink: String
)