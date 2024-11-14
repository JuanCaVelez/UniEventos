package com.unieventos.ui.screens.admin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unieventos.ui.screens.admin.tabs.CouponScreen
import com.unieventos.ui.screens.admin.tabs.EventsScreen
import com.unieventos.ui.screens.admin.tabs.ProfileScreen
import com.unieventos.viewmodel.EventsViewModel
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun NavHostAdmin(
    navController: NavHostController,
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel,
    onNavigateToEventDetailAdmin: (String) -> Unit,
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = ItemTabAdmin.TabEvents
    ) {
        composable<ItemTabAdmin.TabEvents> {
            EventsScreen(
                eventsViewModel = eventsViewModel,
                onNavigateToEventDetailAdmin = {
                    onNavigateToEventDetailAdmin(it)
                }
            )
        }

        composable<ItemTabAdmin.TabCoupons> {
            CouponScreen()
        }

        composable<ItemTabAdmin.TabProfile> {
            ProfileScreen(
                usersViewModel = usersViewModel,
                onNavigateToPasswordRecovery = { }
            )
        }
    }
}