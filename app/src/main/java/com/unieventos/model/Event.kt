package com.unieventos.model

import com.unieventos.model.dto.Location

data class Event(
    var id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val address: String = "",
    val city: String = "",
    //val locations: List<Location>,
    val category: String = "",
    val quantity: String = "",
    val price: String = "",
    val imageUrl: String = ""
)
