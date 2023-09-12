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
import timber.log.Timber

class MainActivity : ComponentActivity() {
    init {
        activity = this
    }

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            DisplayContent()
        }
    }

    @Composable
    private fun DisplayContent() {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                NavigationGraph()
            }
            BottomNavMenu()
        }
    }

    @Composable
    private fun NavigationGraph() {
        NavHost(navController, startDestination = BottomNavItem.Main.screenRoute) {
            composable(BottomNavItem.Settings.screenRoute) {
                TabScreen.SettingsScreen.screen.DisplayContent(this@MainActivity)
            }
            composable(BottomNavItem.Main.screenRoute) {
                TabScreen.MainScreen.screen.DisplayContent(this@MainActivity)
            }
            composable(BottomNavItem.Favorites.screenRoute) {
                TabScreen.FavoriteScreen.screen.DisplayContent(this@MainActivity)
            }
        }
    }

    @Composable
    private fun BottomNavMenu() {
        Application.appComponent.getBottomNavMenu()
            .BottomNavMenu(navController = navController)
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

