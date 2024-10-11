package com.unieventos.model

data class Order(
    val id: String,
    val date: String,
    val total: Double,
    val items: List<OrderItem>,
    val userId: String,
)
