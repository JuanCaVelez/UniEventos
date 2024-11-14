package com.unieventos.ui.screens.user.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun UserCouponScreen(
    onNavigationBack: () -> Unit
){
    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Text(text = "Lista de cupobes usuario")
    }
}