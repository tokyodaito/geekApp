package com.bogsnebes.geekapp.ui.screens.settings.elm

import androidx.lifecycle.MutableLiveData
import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.ui.MainActivity

class Reducer(
    private val state: MutableLiveData<State>,
    private val effect: MutableLiveData<Effect>
) {
    fun internal(event: Event.Internal) {
        when (event) {
            is Event.Internal.ChangeLanguage -> {
                setStateLanguage(event.language)
                MainActivity.recreate()
            }

            is Event.Internal.CurrentLanguage -> {
                setStateLanguage(event.language)
            }

            Event.Internal.ErrorChangeLanguage -> {
                effect.value = Effect.ShowError
            }
        }
    }

    private fun setStateLanguage(language: String) {
        when (language) {
            "en" -> {
                state.value = State(locale = Languages.ENGLISH)
            }

            "de" -> {
                state.value = State(locale = Languages.GERMAN)
            }

            "ru" -> {
                state.value = State(locale = Languages.RUSSIAN)
            }
        }
    }
}