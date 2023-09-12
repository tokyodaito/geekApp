package com.bogsnebes.geekapp.ui.screens.settings.elm

import com.bogsnebes.geekapp.Languages

data class State(
    val locale: Languages
)

sealed class Event {
    sealed class Ui : Event() {
        data object Init : Ui()
        data class ChangeLanguage(val language: Languages) : Ui()
    }

    sealed class Internal : Event() {
        data class ChangeLanguage(val language: String) : Internal()
        data class CurrentLanguage(val language: String) : Internal()
        data object ErrorChangeLanguage : Internal()
    }
}

sealed class Command {
    data object GetCurrentLanguage : Command()
    data class ChangeLanguage(val language: Languages) : Command()
}

sealed class Effect {
    data object ShowError : Effect()
}