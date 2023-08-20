package com.bogsnebes.geekapp.model.dto

data class FactDto(
    val id: String,
    val text: String,
    val source: String,
    val sourceUrl: String,
    val language: String,
    val permalink: String
)
