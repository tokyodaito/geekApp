package com.bogsnebes.geekapp.ui.screens.favorites.elm

import androidx.lifecycle.MutableLiveData

class Reducer(
    private val state: MutableLiveData<State>,
    private val effect: MutableLiveData<Effect>
) {
    fun internal(event: Event.Internal) {
        when (event) {
            is Event.Internal.DataLoaded -> {
                state.value = State(isLoading = false, event.data)
                effect.value = null
            }

            is Event.Internal.ErrorDeleteFavorite -> {
                effect.value = Effect.ShowErrorDeleteFavorite
            }

            is Event.Internal.ErrorLoadingValue -> {
                effect.value = Effect.ShowErrorLoadData
            }
        }
    }
}