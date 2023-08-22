package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer

class Reducer :
    ScreenDslReducer<Event, Event.Ui, Event.Internal, State, Effect, Command>(
        Event.Ui::class, Event.Internal::class
    ) {
    override fun Result.internal(event: Event.Internal): Any {
        return when (event) {
            is Event.Internal.DataLoaded -> {
                state {
                    copy(isLoading = false, data = event.data)
                }
            }

            is Event.Internal.ErrorLoadingData -> {
                state { copy(isLoading = false) }
                effects { +Effect.ShowError }
            }
        }
    }

    override fun Result.ui(event: Event.Ui): Any {
        return when (event) {
            is Event.Ui.Init -> {
                state { copy(isLoading = true) }
                commands { +Command.LoadNewData }
            }

            is Event.Ui.ClickReload -> {
                state { copy(isLoading = true, data = null) }
                commands { +Command.LoadNewData }
            }
        }
    }
}