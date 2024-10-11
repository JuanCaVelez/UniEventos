package com.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unieventos.model.Role
import com.unieventos.ui.screens.CartScreen
import com.unieventos.ui.screens.CreateCouponScreen
import com.unieventos.ui.screens.CreateEventScreen
import com.unieventos.ui.screens.EditProfileScreen
import com.unieventos.ui.screens.EventDetailScreen
import com.unieventos.ui.screens.HomeAdminScreen
import com.unieventos.ui.screens.HomeScreen
import com.unieventos.ui.screens.LoginScreen
import com.unieventos.ui.screens.PasswordRecoveryScreen
import com.unieventos.ui.screens.SignUpScreen
import com.unieventos.ui.screens.UserCouponScreen
import com.unieventos.utils.SharePrefencesUtils
import com.unieventos.viewmodel.EventsViewModel
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun Navigation(
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel
){

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
                onNavigateToCreateCoupon = {
                    navController.navigate(RouteScreen.CreateCoupon)
                },
                onNavigateToEditProfile = {
                    navController.navigate(RouteScreen.EditProfile)
                },
                eventsViewModel = eventsViewModel,
                onNavigateToCreateEvent = {
                    navController.navigate(RouteScreen.CreateEvent)
                },
                onNavigateToDetail = { eventId ->
                    navController.navigate(RouteScreen.EventDetail(eventId))
                },
                onLogout = {
                    SharePrefencesUtils.clearPreferences(context)
                    navController.navigate(RouteScreen.Login){
                        popUpTo(0){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RouteScreen.Home> {
            HomeScreen(
                onNavigateToEditProfile = {
                    navController.navigate(RouteScreen.EditProfile)
                },
                eventsViewModel = eventsViewModel,
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
                onNavigateToCart = {
                    navController.navigate(RouteScreen.Cart)
                },
                onNavigateToUsersCoupons = {
                    navController.navigate(RouteScreen.UsersCoupons)
                }
            )
        }

        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(RouteScreen.SignUp)
                },
                onNavigateHome = { role ->

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
                },
                usersViewModel = usersViewModel
            )
        }

        composable<RouteScreen.SignUp> {
            SignUpScreen(
                onNavigationBack = {
                    navController.popBackStack()
                },
                usersViewModel = usersViewModel
            )
        }

        composable<RouteScreen.Cart> {
            CartScreen(
                onNavigationBack = {
                    navController.popBackStack()
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

        composable<RouteScreen.CreateCoupon>{
            CreateCouponScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteScreen.UsersCoupons>{
            UserCouponScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteScreen.PasswordRecovery> {
            PasswordRecoveryScreen(
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<RouteScreen.EditProfile> {
            EditProfileScreen(
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
                onNavigationBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}