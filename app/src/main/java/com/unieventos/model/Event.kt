package com.unieventos.model

data class Event(
    val id:String,
    val title:String,
    val address: String,
    val city:String,
    val category:String,
    val description: String,
    val quantity: Comparable<*>,
    val price: Comparable<*>,
    val date:String,
    val imageUrl: String
)
