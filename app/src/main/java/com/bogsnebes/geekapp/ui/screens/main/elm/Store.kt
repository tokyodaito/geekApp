package com.bogsnebes.geekapp.ui.screens.main.elm

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class Store : ViewModel() {
    private val _state: MutableLiveData<State> = MutableLiveData()
    val state: LiveData<State>
        get() = _state

    private val _effect: MutableLiveData<Effect> = MutableLiveData()
    val effect: LiveData<Effect>
        get() = _effect

    private val actor = Actor()
    private val reducer = Reducer(_state, _effect)

    fun update(event: Event.Ui) {
        when (event) {
            is Event.Ui.Init -> {
                changeStateToLoad()
            }

            is Event.Ui.ClickReload -> {
                changeStateToLoad()
            }

            is Event.Ui.ChangeLanguage -> {
                executeActor(Command.ChangeLanguage(event.language))
            }

            is Event.Ui.ClickToFavorites -> {
                executeActor(Command.ChangeFavorite(event.data))
            }
        }
    }

    @SuppressLint("CheckResult")
    private fun changeStateToLoad() {
        _state.value = State(isLoading = true, _state.value?.data)
        _effect.value = null
        executeActor(Command.LoadNewData)
    }

    @SuppressLint("CheckResult")
    private fun executeActor(command: Command) {
        actor.execute(command)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                reducer.internal(it)
            }
    }
}