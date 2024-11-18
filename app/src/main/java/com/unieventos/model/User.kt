package com.unieventos.model

data class User(
    val id: String = "",
    val name: String = "",
    val city: String = "",
    val address: String = "",
    val phone: String = "",
    val role: Role = Role.CLIENT,
    val email: String = "",
    val password: String = "",
)