package com.bogsnebes.geekapp.model.impl

import android.annotation.SuppressLint
import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.model.database.dto.FactDto
import com.bogsnebes.geekapp.model.network.api.FactsApi
import com.bogsnebes.geekapp.model.network.json.Fact
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class FactsImpl {
    private val factsApi = Application.networkComponent.getFactsApi()
    private val factsDao = Application.dataBaseComponent.getDatabase().factDao()

    fun getRandomFact(language: FactsApi.Languages): Single<Fact> {
        return factsApi.getRandom(language)
    }

    fun getTodayFact(language: FactsApi.Languages): Single<Fact> {
        return factsApi.getToday(language)
    }

    fun findFactById(id: String): Maybe<FactDto> {
        return Maybe.fromCallable { factsDao.findById(id) }.subscribeOn(Schedulers.io())
    }

    @SuppressLint("CheckResult")
    fun addFactToDatabase(data: FactDto): Completable {
        return Completable.fromAction { factsDao.insert(data) }
            .subscribeOn(Schedulers.io())
            .doOnError { Timber.e(it, "Error of add fact to database") }
    }

    @SuppressLint("CheckResult")
    fun deleteFactOfDatabase(data: FactDto): Completable {
        return Completable.fromAction { factsDao.delete(data) }
            .subscribeOn(Schedulers.io())
            .doOnError { Timber.e(it, "Error of delete fact of database") }
    }

    fun getAll(): Maybe<List<FactDto>> {
        return Maybe.fromCallable { factsDao.getAll() }.subscribeOn(Schedulers.io())
            .doOnError { Timber.e(it, "Error of delete fact of database") }
    }
}