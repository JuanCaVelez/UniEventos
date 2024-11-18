package com.unieventos.model

data class OrderItem(
    val id: String,
    val idEvent: String,
    val numberTickets: Int,
    val localeName: String,
    val price: Double = 0.0
)
