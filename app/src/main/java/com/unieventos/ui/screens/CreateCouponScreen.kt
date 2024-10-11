package com.unieventos.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.ui.components.DropDownMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateCouponScreen(
    onNavigationBack: () -> Unit
){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Crear cupones")},
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
    ) {padding ->
        CreateCouponsForm(
            padding = PaddingValues(0.dp),
            context = context
        )

    }
}

@Composable
fun CreateCouponsForm(
    padding: PaddingValues,
    context: Context
){

    val discounts = listOf("10%", "20%", "30%", "40%", "50%")
    val events = listOf("Comcierto Feid", "Obra de teatro", "Once Caldas vs Deportivo Pereira")

    var discount by rememberSaveable { mutableStateOf("") }
    var event by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DropDownMenu(
            value = discount,
            onValueChange = {
                discount = it
            },
            items = discounts,
            placeholder = "Seleccione el descuento"
        )

        Spacer(modifier = Modifier.height(20.dp))

        DropDownMenu(
            value = event,
            onValueChange = {
                event = it
            },
            items = events,
            placeholder = "Seleccione el evento"
        )

        Spacer(modifier = Modifier.height(30.dp))

        val create = stringResource(id = R.string.couponCreate)

        Button(
            onClick = {
                Toast.makeText(context, create, Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(text = stringResource(id = R.string.createCoupon))
        }
    }
}