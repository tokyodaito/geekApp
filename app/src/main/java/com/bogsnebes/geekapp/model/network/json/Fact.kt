package com.bogsnebes.geekapp.model.network.json

import com.google.gson.annotations.SerializedName

data class Fact(
    @SerializedName("id")
    val id: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("permalink")
    val permalink: String
)
