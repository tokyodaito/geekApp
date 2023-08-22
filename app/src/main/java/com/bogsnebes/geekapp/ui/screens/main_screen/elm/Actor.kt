package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import com.bogsnebes.geekapp.model.TestData
import com.bogsnebes.geekapp.ui.screens.main_screen.elm.Event.Internal.DataLoaded
import io.reactivex.rxjava3.core.Observable
import vivid.money.elmslie.rx3.Actor
import kotlin.random.Random

class Actor : Actor<Command, Event> {
    override fun execute(command: Command): Observable<Event> {
        return when (command) {
            is Command.LoadNewData -> TestData.getData()
                .mapEvents(eventMapper = {
                    DataLoaded(it[Random.nextInt(0, it.size)].text)
                }, errorMapper = {
                    Event.Internal.ErrorLoadingData
                })
        }
    }
}