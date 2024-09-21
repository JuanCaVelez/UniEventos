package com.unieventos.ui.navigation

import kotlinx.serialization.Serializable

sealed class RouteScreen {

    @Serializable
    data object Home: RouteScreen()

    @Serializable
    data object Login: RouteScreen()

    @Serializable
    data object SignUp: RouteScreen()

    @Serializable
    data object Cart: RouteScreen()

    @Serializable
    data object PasswordRecovery: RouteScreen()

    @Serializable
    data object EditProfile: RouteScreen()

    @Serializable
    data object CreateEvent: RouteScreen()


}