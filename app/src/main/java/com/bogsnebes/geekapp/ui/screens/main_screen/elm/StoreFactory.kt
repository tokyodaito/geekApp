package com.bogsnebes.geekapp.ui.screens.main_screen.elm

import vivid.money.elmslie.rx3.ElmStoreCompat

fun storeFactory() = ElmStoreCompat(
    initialState = State(),
    reducer = Reducer(),
    actor = Actor()
).start()