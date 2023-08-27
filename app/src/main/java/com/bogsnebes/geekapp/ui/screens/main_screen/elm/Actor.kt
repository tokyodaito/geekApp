package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.model.impl.FactsImpl
import com.bogsnebes.geekapp.model.network.api.FactsApi
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class Actor {
    private val factsImpl: FactsImpl = Application.appComponent.getFactsImpl()
    fun execute(command: Command): Observable<Event.Internal> {
        return when (command) {
            is Command.LoadNewData -> factsImpl.getRandomFact(FactsApi.ENGLISH)
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMapMaybe {
                    Maybe.fromCallable<Event.Internal> {
                        Event.Internal.DataLoaded(it.text)
                    }
                }
                .switchIfEmpty(Maybe.fromCallable<Event.Internal> { null }.toObservable())
                .onErrorResumeNext {
                    Observable.just(Event.Internal.ErrorLoadingValue)
                }
        }
    }

    fun changeLanguage(languages: Languages) {
        Application.appComponent.getApplication().changeLanguage(languages)
    }
}