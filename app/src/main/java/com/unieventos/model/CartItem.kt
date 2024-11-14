package com.unieventos.model

data class CartItem(
    val id: String,
    val eventId: String,
    val eventName: String,
    val eventImageUrl: String,
    val locationName: String,
    val tickets: Int
)
