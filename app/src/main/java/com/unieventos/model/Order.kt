package com.unieventos.model

data class Order(
    var id: String = "",
    val date: String = "",
    val total: Double = 0.0,
    val items: List<OrderItem> = emptyList(),
    val userId: String = "",
)
