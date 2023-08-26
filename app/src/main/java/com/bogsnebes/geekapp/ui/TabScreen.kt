package com.bogsnebes.geekapp.ui

import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.ui.screens.BaseScreen

sealed class TabScreen {
    abstract val screen: BaseScreen

    data object MainScreen : TabScreen() {
        override val screen: BaseScreen
            get() = Application.appComponent.getMainScreen()
    }
}