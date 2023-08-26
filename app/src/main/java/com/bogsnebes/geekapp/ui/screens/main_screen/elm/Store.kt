package com.bogsnebes.geekapp.ui.screens.main_screen.elm

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

    @SuppressLint("CheckResult")
    fun update(event: Event.Ui) {
        if (event == Event.Ui.Init) {
            _state.value = State(isLoading = true, _state.value?.data)
            _effect.value = null
            actor.execute(Command.LoadNewData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    reducer.internal(it)
                }
        } else {
            _state.value = State(isLoading = true, _state.value?.data)
            _effect.value = null
            actor.execute(Command.LoadNewData)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    reducer.internal(it)
                }
        }
    }
}