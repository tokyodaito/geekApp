package com.bogsnebes.geekapp.ui.screens.favorites.elm

import com.bogsnebes.geekapp.model.database.dto.FactDto

data class State(
    val isLoading: Boolean,
    val data: List<FactDto>?
)

sealed class Event {
    sealed class Ui : Event() {
        data object Init : Ui()
        data class DeleteFavorite(val data: FactDto) : Ui()
    }

    sealed class Internal : Event() {
        data class DataLoaded(val data: List<FactDto>) : Internal()
        data object ErrorLoadingValue : Internal()
        data object ErrorDeleteFavorite : Internal()
    }
}

sealed class Command {
    data object LoadNewData : Command()
    data class DeleteFavorite(val data: FactDto) : Command()
}

sealed class Effect {
    data object ShowErrorLoadData : Effect()
    data object ShowErrorDeleteFavorite : Effect()
}