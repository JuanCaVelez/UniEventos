package com.unieventos.model

import okhttp3.Address

data class User(
    val id: String,
    val name: String,
    val city: String,
    val address: String,
    val phone: String,
    val role: Role,
    val email: String,
    val password: String,
)