package com.bogsnebes.geekapp.ui.screens.main.elm

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.model.database.dto.FactDto
import com.bogsnebes.geekapp.model.impl.FactsImpl
import com.bogsnebes.geekapp.model.network.api.FactsApi
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class Actor {
    private val factsImpl: FactsImpl = Application.dataBaseComponent.getFactsImpl()
    fun execute(command: Command): Observable<Event.Internal> {
        return when (command) {
            is Command.LoadNewData -> factsImpl.getRandomFact(
                when (Application.appComponent.getLingver().getLanguage()) {
                    "de" -> {
                        FactsApi.GERMAN
                    }

                    else -> {
                        FactsApi.ENGLISH
                    }
                }
            )
                .subscribeOn(Schedulers.io())
                .toObservable()
                .flatMapMaybe {
                    Maybe.fromCallable<Event.Internal> {
                        Event.Internal.DataLoaded(
                            FactDto(
                                id = it.id,
                                text = it.text,
                                source = it.source,
                                sourceUrl = it.sourceUrl,
                                language = it.language,
                                permalink = it.permalink
                            )
                        )
                    }
                }
                .switchIfEmpty(Maybe.fromCallable<Event.Internal> { null }.toObservable())
                .doOnError {
                    Timber.e(it, "Error during data loading")
                }
                .onErrorResumeNext {
                    Observable.just(Event.Internal.ErrorLoadingValue)
                }
        }
    }

    fun changeLanguage(languages: Languages): Event.Internal.ChangeLanguage {
        val context = Application.appComponent.getApplicationContext()

        val localeCode = when (languages) {
            Languages.ENGLISH -> "en"
            Languages.GERMAN -> "de"
            Languages.RUSSIAN -> "ru"
        }
        Application.appComponent.getLingver().setLocale(context, localeCode)
        Timber.i("Selected language: %s", Application.appComponent.getLingver().getLocale())
        return Event.Internal.ChangeLanguage
    }

}