package com.unieventos.ui.screens.user.bottomBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun BottomBarUser(
    navController: NavHostController,
    hazeState: HazeState
){
    /*
    val tabs = listOf(

        BottomNavScreen.Cart,
        BottomNavScreen.Events,
        BottomNavScreen.Profile,
        BottomNavScreen.UserCoupons,

    )
    */
    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .hazeChild(state = hazeState)
            .fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        tabs.forEach { tab ->
            val isSelected = currentDestination?.route == tab.route::class.qualifiedName

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(tab.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = tab.title)
                },
                icon = {
                    
                    BadgedBox(
                        badge = {
                            Badge{
                                Text(
                                    text = "2"
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = null
                        )
                    }
                }
            ){

            }
        }
    }
}