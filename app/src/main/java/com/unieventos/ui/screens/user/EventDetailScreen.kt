package com.unieventos.ui.screens.user

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.unieventos.model.CartItem
import com.unieventos.ui.components.FloatingButton
import com.unieventos.viewmodel.CartViewModel
import com.unieventos.viewmodel.EventsViewModel
import java.util.UUID

@Composable
fun EventDetailScreen(
    eventId : String,
    eventsViewModel: EventsViewModel,
    onNavigationBack: () -> Unit,
    cartViewModel: CartViewModel
){
    val context = LocalContext.current
    var showDialog by rememberSaveable { mutableStateOf(false)}
    val event = eventsViewModel.findById(eventId)
    requireNotNull(event)

    Scaffold (
        topBar = {
            TopAppBarDetail(
                title = event.title,
                onNavigationBack = onNavigationBack
            )
        },
        floatingActionButton = {
            FloatingButton(
                onClick = {
                    showDialog = true
                },
                imageVector = Icons.Default.ShoppingCart
            )
        }
    ) { padding ->

        Column (
            modifier = Modifier.padding(padding)
        ){
            val model = ImageRequest.Builder(LocalContext.current)
                .data(event.imageUrl)
                .crossfade(true)
                .build()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = model,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {

                ItemInfoEvent(
                    imageVector = Icons.Filled.Info,
                    text = event.description
                )

                Spacer(modifier = Modifier.height(16.dp))

                ItemInfoEvent(
                    imageVector = Icons.Filled.CalendarMonth,
                    text = event.date
                )
                
                Spacer(modifier = Modifier.height(15.dp))

                ItemInfoEvent(
                    imageVector = Icons.Filled.LocationOn,
                    text = event.city
                )

                Spacer(modifier = Modifier.height(15.dp))

                ItemInfoEvent(
                    imageVector = Icons.Filled.Person,
                    text = event.quantity.toString()
                )

                Spacer(modifier = Modifier.height(15.dp))

                ItemInfoEvent(
                    imageVector = Icons.Filled.AttachMoney,
                    text = event.price.toString()
                )
            }
        }
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        cartViewModel.addToCart(
                            CartItem(
                                id = UUID.randomUUID().toString(),
                                eventId = event.id,
                                eventName = event.title,
                                eventImageUrl = event.imageUrl,
                                locationName = " ",
                                tickets = 3
                            )
                        )
                        showDialog = false
                        Toast.makeText(context, "Evento Agregado al carrito", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text(
                        text = "Confirmar"
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(
                        text = "Cancelar"
                    )
                }
            },
            title = {
                Text(text = "Agregar al carrito")
            },
            text = {
                Text(
                    text = "¿Desea agregar este evento al carrito?"
                )
            }
        )
    }

}

@Composable
fun ItemInfoEvent(
    imageVector: ImageVector,
    text: String
){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = imageVector,
            contentDescription = text
        )
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarDetail(
    title: String,
    onNavigationBack: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavigationBack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}
