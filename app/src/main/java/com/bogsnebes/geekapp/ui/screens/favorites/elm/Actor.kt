package com.bogsnebes.geekapp.ui.screens.favorites.elm

import android.annotation.SuppressLint
import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.model.database.dto.FactDto
import com.bogsnebes.geekapp.model.impl.FactsImpl
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

class Actor {
    private val factsImpl: FactsImpl = Application.dataBaseComponent.getFactsImpl()
    fun execute(command: Command): Observable<Event.Internal> {
        return when (command) {
            is Command.LoadNewData -> getData()

            is Command.DeleteFavorite -> deleteFavorite(command.data)
        }
    }

    private fun getData(): Observable<Event.Internal> {
        return factsImpl.getAll()
            .toObservable()
            .flatMapMaybe {
                Maybe.fromCallable<Event.Internal> {
                    Event.Internal.DataLoaded(it)
                }
            }
            .switchIfEmpty(Maybe.fromCallable<Event.Internal> { null }
                .toObservable())
            .doOnError {
                Timber.e(it, "Error during data loading")
            }
            .onErrorResumeNext {
                Observable.just(Event.Internal.ErrorLoadingValue)
            }
    }

    @SuppressLint("CheckResult")
    private fun deleteFavorite(fact: FactDto): Observable<Event.Internal> {
        return factsImpl.deleteFactOfDatabase(fact)
            .andThen(getData())
            .doOnError {
                Timber.e(it, "Error during delete")
            }
            .onErrorResumeNext {
                Observable.just(Event.Internal.ErrorDeleteFavorite)
            }
    }
}
