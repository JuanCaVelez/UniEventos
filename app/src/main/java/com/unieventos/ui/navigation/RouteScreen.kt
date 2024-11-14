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
    data class Cart(val eventId: String): RouteScreen()

    @Serializable
    data object PasswordRecovery: RouteScreen()

    @Serializable
    data object EditProfile: RouteScreen()

    @Serializable
    data object CreateEvent: RouteScreen()

    @Serializable
    data object HomeAdmin: RouteScreen()

    @Serializable
    data object CreateCoupon: RouteScreen()

    @Serializable
    data object UsersCoupons: RouteScreen()

    @Serializable
    data class EventDetail(val eventId: String): RouteScreen()

    @Serializable
    data class EventDetailAdmin(val eventId: String): RouteScreen()
}