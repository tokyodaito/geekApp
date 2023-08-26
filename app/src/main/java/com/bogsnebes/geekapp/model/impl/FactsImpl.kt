package com.bogsnebes.geekapp.model.impl

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.model.network.api.FactsApi
import com.bogsnebes.geekapp.model.network.json.Fact
import io.reactivex.rxjava3.core.Single

class FactsImpl {
    private val factsApi = Application.appComponent.getRetrofit().create(FactsApi::class.java)

    fun getRandomFact(language: FactsApi.Languages): Single<Fact> {
        return factsApi.getRandom(language)
    }

    fun getTodayFact(language: FactsApi.Languages): Single<Fact> {
        return factsApi.getToday(language)
    }
}