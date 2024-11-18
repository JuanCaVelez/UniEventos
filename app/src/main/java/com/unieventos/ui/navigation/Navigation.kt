package com.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unieventos.model.Role
import com.unieventos.ui.screens.user.tabs.CartScreen
import com.unieventos.ui.screens.admin.CreateEventScreen
import com.unieventos.ui.screens.user.tabs.ProfileScreen
import com.unieventos.ui.screens.admin.EventDetaiLAdminScreen
import com.unieventos.ui.screens.user.EventDetailScreen
import com.unieventos.ui.screens.admin.HomeAdminScreen
import com.unieventos.ui.screens.user.HomeScreen
import com.unieventos.ui.screens.account.LoginScreen
import com.unieventos.ui.screens.account.PasswordRecoveryScreen
import com.unieventos.ui.screens.account.SignUpScreen
import com.unieventos.utils.SharePrefencesUtils
import com.unieventos.viewmodel.CartViewModel
import com.unieventos.viewmodel.EventsViewModel
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun Navigation(
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel,
    cartViewModel: CartViewModel
){

    val currentUser by usersViewModel.currentUser.collectAsState()
    val navController = rememberNavController()
    val context = LocalContext.current

    var startDestination: RouteScreen = RouteScreen.Login
    val sesion = SharePrefencesUtils.getCurrentUser(context)

    if(sesion != null){
        startDestination = if(sesion.role == Role.ADMIN){
            RouteScreen.HomeAdmin
        }else{
            RouteScreen.Home
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable<RouteScreen.HomeAdmin> {
            HomeAdminScreen(
                eventsViewModel = eventsViewModel,
                onNavigateToCreateEvent = {
                    navController.navigate(RouteScreen.CreateEvent)
                },
                onNavigateToEventDetailAdmin = { eventId ->
                    navController.navigate(RouteScreen.EventDetailAdmin(eventId))
                },
                onLogout = {
                    SharePrefencesUtils.clearPreferences(context)
                    navController.navigate(RouteScreen.Login){
                        popUpTo(0){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                usersViewModel = usersViewModel
            )
        }

        composable<RouteScreen.Home> {
            HomeScreen(
                eventsViewModel = eventsViewModel,
                cartViewModel = cartViewModel,
                onNavigateToDetail = { eventId ->
                    navController.navigate(RouteScreen.EventDetail(eventId))
                },
                onLogout = {
                    SharePrefencesUtils.clearPreferences(context)
                    navController.navigate(RouteScreen.Login){
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToCart = { eventId ->
                    navController.navigate(RouteScreen.Cart(eventId))
                },
                usersViewModel = usersViewModel,
                onNavigateToPasswordRecovery = {
                    navController.navigate(RouteScreen.PasswordRecovery)
                }
            )
        }

        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(RouteScreen.SignUp)
                },
                onNavigateHome = {

                    val user = currentUser

                    if(user != null){

                        val role = user.role
                        SharePrefencesUtils.savePreferences(context, user.id, user.role)

                        val home = if(role == Role.CLIENT){
                            RouteScreen.Home
                        }else{
                            RouteScreen.HomeAdmin
                        }
                        navController.navigate(home){
                            popUpTo(0){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                },
                usersViewModel = usersViewModel,
                onNavigateToPassworRecoveryScreen = {
                    navController.navigate(RouteScreen.PasswordRecovery)
                }
            )
        }

        composable<RouteScreen.SignUp> {
            SignUpScreen(
                onNavigationBack = {
                    navController.popBackStack()
                },
                usersViewModel = usersViewModel,
                onNavigateHome = {
                    navController.navigate(RouteScreen.Login)
                }
            )
        }

        composable<RouteScreen.CreateEvent>{
            CreateEventScreen(
                onNavigationBack = {
                    navController.popBackStack()
                },
                eventsViewModel = eventsViewModel,
                onNavigationHome = {
                    navController.navigate(RouteScreen.HomeAdmin)
                }
            )
        }

        /*
        composable<RouteScreen.CreateCoupon>{
            CouponScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }
         */

        /*
        composable<RouteScreen.UsersCoupons>{
            UserCouponScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }
        */
        composable<RouteScreen.PasswordRecovery> {
            PasswordRecoveryScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteScreen.EditProfile> {
            ProfileScreen(
                usersViewModel = usersViewModel,
                onNavigateToPasswordRecovery = {
                    navController.navigate(RouteScreen.PasswordRecovery)
                },
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteScreen.EventDetail> {
            val eventId = it.toRoute<RouteScreen.EventDetail>()
            EventDetailScreen(
                eventId = eventId.eventId,
                eventsViewModel = eventsViewModel,
                cartViewModel = cartViewModel,
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteScreen.EventDetailAdmin> {
            val eventId = it.toRoute<RouteScreen.EventDetailAdmin>()
            EventDetaiLAdminScreen(
                eventId = eventId.eventId,
                eventsViewModel = eventsViewModel,
                onNavigationBack = {
                    navController.popBackStack()
                },
                onNavigationHome = {
                    navController.navigate(RouteScreen.HomeAdmin)
                }
            )
        }
    }
}