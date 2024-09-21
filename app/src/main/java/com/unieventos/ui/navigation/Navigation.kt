package com.unieventos.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.screens.CartScreen
import com.unieventos.ui.screens.CreateEventScreen
import com.unieventos.ui.screens.EditProfileScreen
import com.unieventos.ui.screens.HomeScreen
import com.unieventos.ui.screens.LoginScreen
import com.unieventos.ui.screens.PasswordRecoveryScreen
import com.unieventos.ui.screens.SignUpScreen

@Composable
fun Navigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteScreen.CreateEvent
    ){

        composable<RouteScreen.Home> {
            HomeScreen(
                onNavigateToCart = {
                    navController.navigate(RouteScreen.Cart)
                }
            )
        }

        composable<RouteScreen.Login> {
            LoginScreen(
                onNavigateToSignUp = {
                    navController.navigate(RouteScreen.SignUp)
                },
                onNavigateHome = {
                    navController.navigate(RouteScreen.Home){
                        popUpTo(0){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RouteScreen.SignUp> {
            SignUpScreen(
                onNavigateToHome = {
                    navController.navigate(RouteScreen.Home){
                        popUpTo(0){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable<RouteScreen.Cart> {
            CartScreen()
        }

        composable<RouteScreen.PasswordRecovery> {
            PasswordRecoveryScreen()
        }

        composable<RouteScreen.EditProfile> {
            EditProfileScreen()
        }

        composable<RouteScreen.CreateEvent> {
            CreateEventScreen()
        }

    }

}