package com.bogsnebes.geekapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bogsnebes.geekapp.Application
import com.bogsnebes.geekapp.ui.elements.bottom_navigation_menu.BottomNavItem
import com.bogsnebes.geekapp.ui.screens.BaseScreen
import timber.log.Timber

class MainActivity : ComponentActivity() {
    private val screens = setOf<TabScreen>(TabScreen.MainScreen)

    override fun onCreate(savedInstanceState: Bundle?) {
        activity = this

        val currentScreen: BaseScreen =
            screens.find { it == TabScreen.MainScreen }?.screen ?: TabScreen.MainScreen.screen

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavigationGraph(navController = navController)

            Column {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.weight(1f)) {
                        currentScreen.DisplayContent(this@MainActivity)
                    }
                    Application.appComponent.getBottomNavMenu()
                        .BottomNavMenu(this@MainActivity, navController = navController)
                }
            }
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController, startDestination = BottomNavItem.Main.screenRoute) {
            composable(BottomNavItem.Settings.screenRoute) {

            }
            composable(BottomNavItem.Main.screenRoute) {
                Application.appComponent.getMainScreen()
            }
            composable(BottomNavItem.Favorites.screenRoute) {

            }
        }
    }

    companion object {
        private lateinit var activity: MainActivity
        fun recreate() {
            try {
                activity.recreate()
            } catch (error: Exception) {
                Timber.e("Recreate of MainActivity failed: $error")
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

