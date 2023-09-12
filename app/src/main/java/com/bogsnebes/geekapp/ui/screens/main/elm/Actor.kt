package com.bogsnebes.geekapp.ui.screens.main.elm

import android.annotation.SuppressLint
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
            is Command.LoadNewData -> getData()

            is Command.ChangeFavorite -> {
                command.data?.let { changeFavorite(it) }
                    ?: Observable.just(Event.Internal.ErrorLoadingValue)
            }

            is Command.ChangeLanguage -> {
                changeLanguage(command.language)
            }

            is Command.CheckFavorite -> {
                checkFavorite(command.id)
            }
        }
    }

    private fun getData(): Observable<Event.Internal> {
        return factsImpl.getRandomFact(
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
            .flatMapMaybe { fact ->
                factsImpl.findFactById(fact.id)
                    .isEmpty
                    .flatMapMaybe {
                        Maybe.fromCallable<Event.Internal> {
                            Event.Internal.DataLoaded(
                                FactUi(
                                    FactDto(
                                        id = fact.id,
                                        text = fact.text,
                                        source = fact.source,
                                        sourceUrl = fact.sourceUrl,
                                        language = fact.language,
                                        permalink = fact.permalink
                                    ), !it
                                )
                            )
                        }
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

    @SuppressLint("CheckResult")
    private fun checkFavorite(id: String): Observable<Event.Internal> {
        return factsImpl.findFactById(id)
            .isEmpty
            .flatMapMaybe {
                Maybe.fromCallable<Event.Internal> {
                    Event.Internal.StatusFavorite(!it)
                }
            }.toObservable()
            .doOnError {
                Timber.e(it, "Error during check favorite")
            }
            .onErrorResumeNext {
                Observable.just(Event.Internal.ErrorCheckFavorite)
            }
    }

    private fun changeLanguage(languages: Languages): Observable<Event.Internal> {
        val context = Application.appComponent.getApplicationContext()

        val localeCode = when (languages) {
            Languages.ENGLISH -> "en"
            Languages.GERMAN -> "de"
            Languages.RUSSIAN -> "ru"
        }

        Application.appComponent.getLingver().setLocale(context, localeCode)
        Timber.i("Selected language: %s", Application.appComponent.getLingver().getLocale())
        return Observable.just(Event.Internal.ChangeLanguage)
    }

    private fun changeFavorite(data: FactDto): Observable<Event.Internal> {
        return factsImpl.findFactById(data.id)
            .subscribeOn(Schedulers.io())
            .isEmpty
            .flatMapObservable { isEmpty ->
                if (isEmpty) {
                    factsImpl.addFactToDatabase(data)
                        .andThen(Observable.just(Event.Internal.AddFavorite))
                } else {
                    factsImpl.deleteFactOfDatabase(data)
                        .andThen(Observable.just(Event.Internal.DeleteFavorite))
                }
            }
            .onErrorReturn {
                Timber.e(it, "Error changeFavorite")
                Event.Internal.ErrorLoadingValue
            }
    }
}