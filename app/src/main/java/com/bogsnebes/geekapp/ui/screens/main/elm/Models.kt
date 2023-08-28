package com.bogsnebes.geekapp.ui.screens.main.elm

import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.model.database.dto.FactDto

data class State(
    val isLoading: Boolean,
    val data: FactDto?
)

sealed class Event {
    sealed class Ui : Event() {
        data object Init : Ui()
        data object ClickReload : Ui()
        data class ChangeLanguage(val language: Languages) : Ui()
        data class ClickToFavorites(val data: FactDto?) : Ui()
    }

    sealed class Internal : Event() {
        data class DataLoaded(val data: FactDto) : Internal()
        data object ErrorLoadingValue : Internal()
        data object ChangeLanguage : Internal()
    }
}

sealed class Command {
    data object LoadNewData : Command()
}

sealed class Effect {
    data object ShowError : Effect()
}