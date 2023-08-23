package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import com.bogsnebes.geekapp.model.TestData
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.random.Random

class Actor {
    fun execute(command: Command): Observable<Event.Internal> {
        return when (command) {
            is Command.LoadNewData -> TestData.getData()
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMapMaybe {
                    Maybe.fromCallable<Event.Internal> {
                        Event.Internal.DataLoaded(it[Random.nextInt(0, it.size)].text)
                    }
                }
                .switchIfEmpty(Maybe.fromCallable<Event.Internal> { null }.toObservable())
                .onErrorResumeNext {
                    Observable.just(Event.Internal.ErrorLoadingValue)
                }
        }
    }
}