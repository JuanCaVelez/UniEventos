package com.unieventos.ui.screens.user.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.unieventos.model.CartItem
import com.unieventos.viewmodel.CartViewModel

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
){

    val itemsCart = cartViewModel.getItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemsCart){
            ItemCartView(it)
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Carrito de compras")
    }
}

@Composable
fun ItemCartView(
    cartItem: CartItem
){
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = cartItem.eventImageUrl,
                contentDescription = null
            )

            Column {
                Text(text = cartItem.eventName)
                Text(text = cartItem.tickets.toString())
            }
        }
    }
}