package com.bogsnebes.geekapp.model.network.api

import com.bogsnebes.geekapp.model.network.json.Fact
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsApi {
    @GET("/api/v2/facts/random")
    fun getRandom(@Query("language") language: Languages): Single<Fact>

    @GET("/api/v2/facts/today")
    fun getToday(@Query("language") language: Languages): Single<Fact>

    @JvmInline
    value class Languages(val code: String)

    companion object {
        val ENGLISH = Languages("en")
        val GERMAN = Languages("de")
    }
}