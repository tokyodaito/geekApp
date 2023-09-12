package com.bogsnebes.geekapp.ui.screens.main.elm

import androidx.lifecycle.MutableLiveData
import com.bogsnebes.geekapp.ui.MainActivity

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

            is Event.Internal.ChangeLanguage -> {
                MainActivity.recreate()
            }

            is Event.Internal.AddFavorite -> {
                setFavoriteStatus(true)
            }

            Event.Internal.DeleteFavorite -> {
                setFavoriteStatus(false)
            }
        }
    }

    private fun setFavoriteStatus(status: Boolean) {
        state.value =
            state.value?.let {
                State(it.isLoading,
                    it.data?.fact?.let { factDto -> FactUi(factDto, status) }
                )
            }
    }
}