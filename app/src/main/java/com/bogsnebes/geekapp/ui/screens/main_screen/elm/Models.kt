package com.bogsnebes.geekapp.ui.screens.main_screen.elm

data class State(
    val isLoading: Boolean = false,
    val data: String? = null
)

sealed class Event {

    sealed class Ui : Event() {
        data object Init : Ui()
        data object ClickReload : Ui()
    }

    sealed class Internal : Event() {
        data class DataLoaded(val data: String) : Internal()
        data object ErrorLoadingData : Internal()
    }
}

sealed class Command {
    data object LoadNewData : Command()
}

sealed class Effect {
    data object ShowError : Effect()
}
