package com.bogsnebes.geekapp.ui.elements.bottom_navigation_menu

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bogsnebes.geekapp.R

class BottomNavMenu {
    @SuppressLint("NotConstructor")
    @Composable
    fun BottomNavMenu(context: Context, navController: NavController) {
        val items = listOf(
            BottomNavItem.Settings,
            BottomNavItem.Main,
            BottomNavItem.Favorites,
        )

        BottomNavigation(
            backgroundColor = colorResource(id = R.color.teal_200),
            contentColor = Color.Black
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = context.getString(item.titleResId)
                        )
                    },
                    label = {
                        Text(
                            text = context.getString(item.titleResId),
                            fontSize = 9.sp
                        )
                    },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screenRoute,
                    onClick = {
                        navController.navigate(item.screenRoute) {

                            navController.graph.startDestinationRoute?.let { screenRoute ->
                                popUpTo(screenRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}