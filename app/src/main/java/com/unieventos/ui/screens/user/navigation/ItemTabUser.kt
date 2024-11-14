package com.unieventos.ui.screens.user.navigation

import kotlinx.serialization.Serializable

sealed class ItemTabUser {

    @Serializable
    data object TabEvents: ItemTabUser()

    @Serializable
    data object TabCoupons: ItemTabUser()

    @Serializable
    data object TabProfile: ItemTabUser()

    @Serializable
    data object TabCart: ItemTabUser()
}