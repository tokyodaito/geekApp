package com.bogsnebes.geekapp.ui.screens.settings.elm

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.Languages
import io.reactivex.rxjava3.core.Observable
import timber.log.Timber

class Actor {
    fun execute(command: Command): Observable<Event.Internal> {
        return when (command) {
            is Command.GetCurrentLanguage -> getCurrentLanguage()
            is Command.ChangeLanguage -> changeLanguage(command.language)
        }
    }

    private fun getCurrentLanguage(): Observable<Event.Internal> {
        return Observable.just(
            Event.Internal.CurrentLanguage(
                Application.appComponent.getLingver().getLocale().toString()
            )
        )
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
        return Observable.just(
            Event.Internal.ChangeLanguage(
                Application.appComponent.getLingver().getLocale().toString()
            )
        )
    }
}