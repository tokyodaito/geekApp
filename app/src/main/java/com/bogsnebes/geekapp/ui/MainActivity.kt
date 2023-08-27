package com.bogsnebes.geekapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.ui.screens.BaseScreen

class MainActivity : ComponentActivity() {
    private val screens = setOf<TabScreen>(TabScreen.MainScreen)

    override fun onCreate(savedInstanceState: Bundle?) {

        val currentScreen: BaseScreen =
            screens.find { it == TabScreen.MainScreen }?.screen ?: TabScreen.MainScreen.screen

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Column() {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        currentScreen.DisplayContent()
                    }
                    Application.appComponent.getBottomNavMenu()
                        .BottomNavMenu(navController = navController)
                }
            }
        }
    }
}

/* TODO: Вступительный ViewPager
* Сделать аналог ViewPager на Compose для отображения информации о приложении
* */

/* TODO: BottomNavigation
 Сделать аналог BottomNavigation для перемещения между фактами в избранном и обычными
 */

