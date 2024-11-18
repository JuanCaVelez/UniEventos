package com.unieventos.ui.screens.admin.bottomBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.LocalActivity
import androidx.compose.material.icons.rounded.Loyalty
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.unieventos.ui.screens.admin.navigation.ItemTabAdmin
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun BottomBarAdmin(
    navController: NavHostController,
    hazeState: HazeState
) {

    val tabs = listOf(
        BottomNavScreen.Events,
        BottomNavScreen.Coupons,
        BottomNavScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationBar(
        containerColor = Color.Transparent,
        modifier = Modifier
            .hazeChild(state = hazeState)
            .fillMaxWidth()
    ) {
        tabs.forEach { tab ->

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = null,
                    )
                },
                onClick = {
                    navController.navigate(tab.route)
                },
                label = {
                    Text(
                        text = tab.title,
                    )
                },
                selected = navBackStackEntry?.destination?.hasRoute(tab.route::class) ?: false
            )
        }
    }
}

sealed class BottomNavScreen(val route: ItemTabAdmin, val title: String, val icon: ImageVector){
    data object Events: BottomNavScreen(
        route = ItemTabAdmin.TabEvents,
        title = "Eventos",
        icon = Icons.Rounded.LocalActivity
    )

    data object Coupons: BottomNavScreen(
        route = ItemTabAdmin.TabCoupons,
        title = "Cupones",
        icon = Icons.Rounded.Loyalty
    )

    data object Profile: BottomNavScreen(
        route = ItemTabAdmin.TabProfile,
        title = "Perfil",
        icon = Icons.Default.Person
    )
}
