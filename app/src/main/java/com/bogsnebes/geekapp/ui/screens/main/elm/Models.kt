package com.bogsnebes.geekapp.ui.screens.main.elm

import com.bogsnebes.geekapp.Languages
import com.bogsnebes.geekapp.model.database.dto.FactDto

data class FactUi(
    val fact: FactDto,
    val favorite: Boolean
)

data class State(
    val isLoading: Boolean,
    val data: FactUi?
)

sealed class Event {
    sealed class Ui : Event() {
        data object Init : Ui()
        data object ClickReload : Ui()
        data class ChangeLanguage(val language: Languages) : Ui()
        data class ClickToFavorites(val data: FactDto?) : Ui()
    }

    sealed class Internal : Event() {
        data class DataLoaded(val data: FactUi) : Internal()
        data object ErrorLoadingValue : Internal()
        data object ChangeLanguage : Internal()

        data object DeleteFavorite : Internal()

        data object AddFavorite : Internal()
    }
}

sealed class Command {
    data object LoadNewData : Command()
    data class ChangeLanguage(val language: Languages) : Command()

    data class ChangeFavorite(val data: FactDto?) : Command()
}

sealed class Effect {
    data object ShowError : Effect()
}