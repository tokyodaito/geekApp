package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import androidx.lifecycle.MutableLiveData

class Reducer(
    private val state: MutableLiveData<State>,
    private val effect: MutableLiveData<Effect>
) {
    fun internal(event: Event.Internal) {
        return when (event) {
            is Event.Internal.DataLoaded -> {
                state.value = State(isLoading = false, data = event.data)
            }

            is Event.Internal.ErrorLoadingValue -> {
                state.value = State(isLoading = false, data = null)
                effect.value = Effect.ShowError
            }
        }
    }
}