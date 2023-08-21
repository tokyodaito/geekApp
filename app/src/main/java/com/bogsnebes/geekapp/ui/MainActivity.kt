package com.bogsnebes.geekapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bogsnebes.geekapp.ui.screens.BaseScreen

class MainActivity : ComponentActivity() {
    private val screens = setOf<TabScreen>(TabScreen.MainScreen)

    override fun onCreate(savedInstanceState: Bundle?) {
        val currentScreen: BaseScreen =
            screens.find { it == TabScreen.MainScreen }?.screen ?: TabScreen.MainScreen.screen

        super.onCreate(savedInstanceState)
        setContent {
            currentScreen.Content()
        }
    }
}

/* TODO: Вступительный ViewPager
* Сделать аналог ViewPager на Compose для отображения информации о приложении
* */

/* TODO: BottomNavigation
 Сделать аналог BottomNavigation для перемещения между фактами в избранном и обычными
 */

