package com.unieventos.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.unieventos.R
import com.unieventos.ui.components.DropDownMenu
import com.unieventos.ui.components.TextFieldForm
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CreateEventScreen(

){
    val context = LocalContext.current

    Scaffold { padding ->
        CreateEventForm(
            padding = padding,
            context = context,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventForm(
    padding: PaddingValues,
    context: Context,
) {

    val citys = listOf("Armenia", "Pereira", "Manizales")
    val categories = listOf("Conciertos", "Obras de teatro", "Partidos Futbol")

    var eventName by rememberSaveable { mutableStateOf("") }
    var eventAddress by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var dateOfEvent by rememberSaveable { mutableStateOf("") }
    var showDatePicker by rememberSaveable { mutableStateOf (false) }
    var datePickerState = rememberDatePickerState()



    Column (
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextFieldForm(
            value = eventName,
            onValueChange = {
                eventName = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventName),
            onValidate = {
                eventName.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldForm(
            value = eventAddress,
            onValueChange = {
                eventAddress = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventAddress),
            onValidate = {
                eventAddress.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        DropDownMenu(
            value = city,
            onValueChange = {
                city = it
            },
            items = citys
        )

        DropDownMenu(
            value = category,
            onValueChange = {
                category = it
            },
            items =  categories

        )


        TextFieldForm(
            value = description,
            onValueChange = {
                description = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventDescription),
            onValidate = {
                description.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldForm(
            value = quantity,
            onValueChange = {
                quantity = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventQuantity),
            onValidate = {
                quantity.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextFieldForm(
            value = price,
            onValueChange = {
                price = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventPrice),
            onValidate = {
                price.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = dateOfEvent,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = stringResource(id = R.string.eventDate))
            },
            trailingIcon = {
                IconButton(
                    onClick = {showDatePicker = true}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = "Icono de fecha de nacimiento"
                    )
                }
            }
        )

        Button(
            onClick = {

            }
        ) {
            Text(text = stringResource(id = R.string.create))
        }

        if(showDatePicker){
            DatePickerDialog(
                onDismissRequest = {showDatePicker = false},
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selectedDate = datePickerState.selectedDateMillis
                            if(selectedDate != null){
                                val date = Date(selectedDate)
                                val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
                                dateOfEvent = formattedDate
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text(text = stringResource(id = R.string.confirmLabel))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {showDatePicker = false}
                    ) {
                        Text(text = stringResource(id = R.string.cancelLabel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

