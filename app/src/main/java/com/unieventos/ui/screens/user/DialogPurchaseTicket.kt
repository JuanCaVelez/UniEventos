package com.unieventos.ui.screens.user

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unieventos.model.CartItem
import com.unieventos.model.Order
import com.unieventos.ui.components.DropDownMenu
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.viewmodel.CartViewModel
import com.unieventos.viewmodel.OrdersViewModel
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun DialogPurchaseTicket(
    cartViewModel: CartViewModel,
    ordersViewModel: OrdersViewModel = viewModel(),
    onDismiss: () -> Unit
){
    var showDiscount by remember { mutableStateOf(false) }
    var cupon by remember { mutableStateOf("") }
    var metodoPago by remember { mutableStateOf("") }
    val items = listOf("Efectivo", "Tarjeta", "Transferencia")


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {

                    ordersViewModel.createOrder(
                        Order(
                            LocalDateTime.now().toString(),

                        )
                    )
                    onDismiss()
                }
            ) {
                Text(
                    text = "Confirmar"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
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
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextFieldForm(
                        modifier = Modifier.weight(1f),
                        value = cupon,
                        onValueChange = {
                            cupon = it
                        },
                        supportingText = "",
                        label = "cupón",
                        onValidate = {cupon.isEmpty() || cupon.isNotEmpty()},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )

                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            
                        }
                    ) {
                        Text(
                            text = "aplicar"
                        )
                    }
                    
                    DropDownMenu(
                        value = metodoPago,
                        onValueChange = {
                            metodoPago = it
                        },
                        items = items.map { it },
                        placeholder = "",
                        label = ""
                    )

                    /*
                    if(showDiscount){
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Total: $${cartViewModel.}",

                        )
                     */
                }
            }
        }
    )
}