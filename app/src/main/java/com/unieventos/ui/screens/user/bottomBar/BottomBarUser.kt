package com.unieventos.ui.screens.user.bottomBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocalActivity
import androidx.compose.material.icons.rounded.Loyalty
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import com.unieventos.R
import com.unieventos.ui.screens.user.navigation.ItemTabUser
import com.unieventos.viewmodel.CartViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun BottomBarUser(
    navController: NavController,
    hazeState: HazeState,
    cartViewModel: CartViewModel
){
    val tabs = listOf(
        NavigationBarUser(
            route = ItemTabUser.TabEvents,
            title = stringResource(id = R.string.homeNavigationLabel),
            icon = Icons.Outlined.Home,
            iconSelected = Icons.Filled.Home
        ),

        NavigationBarUser(
            route = ItemTabUser.TabCoupons,
            title = stringResource(id = R.string.couponNavigationLabel),
            icon = Icons.Outlined.Search,
            iconSelected = Icons.Filled.Search
        ),

        NavigationBarUser(
            route = ItemTabUser.TabCart,
            title = stringResource(id = R.string.cartNavigationLabel),
            icon = Icons.Outlined.ShoppingBasket,
            iconSelected = Icons.Filled.ShoppingBasket
        ),

        NavigationBarUser(
            route = ItemTabUser.TabProfile,
            title = stringResource(id = R.string.profileNavigationLabel),
            icon = Icons.Outlined.Person,
            iconSelected = Icons.Filled.Person
        )
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
                    if(tab.route == ItemTabUser.TabCart){
                        BadgedBox(
                            badge = {
                                Badge{
                                    Text(
                                        text = "${cartViewModel.countItems()}"
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = null
                            )
                        }
                    }else{
                        Icon(
                            imageVector = tab.icon,
                            contentDescription = null
                        )
                    }
                },
                onClick = {
                    navController.navigate(tab.route)
                },
                label = {
                    Text(
                        text = tab.title
                    )
                },
                selected = navBackStackEntry?.destination?.hasRoute(tab.route::class) ?: false
            )
        }
    }
}

data class NavigationBarUser(val route: ItemTabUser, val title: String, val icon: ImageVector, val iconSelected: ImageVector)