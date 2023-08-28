package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.model.network.api.FactsApi
import org.intellij.lang.annotations.Language

data class State(
    val isLoading: Boolean,
    val data: String?
)

sealed class Event {
    sealed class Ui : Event() {
        data object Init : Ui()
        data object ClickReload : Ui()
        data class ChangeLanguage(val language: Languages) : Ui()
    }

    sealed class Internal : Event() {
        data class DataLoaded(val data: String) : Internal()
        data object ErrorLoadingValue : Internal()
    }
}

sealed class Command {
    data object LoadNewData : Command()
}

sealed class Effect {
    data object ShowError : Effect()
}