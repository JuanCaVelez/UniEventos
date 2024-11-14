package com.unieventos.ui.screens.user.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unieventos.ui.screens.user.tabs.CartScreen
import com.unieventos.ui.screens.user.tabs.UserCouponScreen
import com.unieventos.viewmodel.EventsViewModel
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun NavHostUser(
    navController: NavHostController,
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToCart: (String) -> Unit,
    onNavigateToPasswordRecovery: () -> Unit,
){
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = ItemTabUser.TabEvents
    ){
        composable<ItemTabUser.TabEvents> {
            com.unieventos.ui.screens.user.tabs.EventsScreen(
                onNavigateToDetail = {
                    onNavigateToDetail(it)
                },
                onNavigateToCart = {
                    onNavigateToCart(it)
                },
                eventsViewModel = eventsViewModel
            )
        }
        composable<ItemTabUser.TabCoupons> {
            UserCouponScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<ItemTabUser.TabCart> {
            CartScreen(
                eventId = id.toString(),
                eventsViewModel = eventsViewModel,
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<ItemTabUser.TabProfile> {
            com.unieventos.ui.screens.user.tabs.ProfileScreen(
                usersViewModel = usersViewModel,
                onNavigateToPasswordRecovery = {
                    onNavigateToPasswordRecovery()
                },
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}