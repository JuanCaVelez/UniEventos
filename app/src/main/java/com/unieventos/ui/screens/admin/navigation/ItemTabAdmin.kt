package com.unieventos.ui.screens.admin.navigation

import kotlinx.serialization.Serializable

sealed class ItemTabAdmin {

    @Serializable
    data object TabEvents: ItemTabAdmin()

    @Serializable
    data object TabCoupons: ItemTabAdmin()

    @Serializable
    data object TabProfile: ItemTabAdmin()

}