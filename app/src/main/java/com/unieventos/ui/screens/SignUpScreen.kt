package com.unieventos.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.ui.components.DropDownMenu
import com.unieventos.ui.components.TextFieldForm
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SignUpScreen(
    onNavigateToHome: () -> Unit
){

    val context = LocalContext.current

    Scaffold { padding ->
        SignUpForm(
            padding = padding,
            context = context,
            onNavigateToHome
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(
    padding: PaddingValues,
    context: Context,
    onNavigateToHome: () -> Unit
){

    val citys = listOf("Armenia", "Pereira", "Manizales")

    var name by rememberSaveable { mutableStateOf("") }
    var cedula by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }


    var dateOfBirth by rememberSaveable { mutableStateOf("") }
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
            value = name,
            onValueChange = {
                name = it
            },
            supportingText = stringResource(id = R.string.nameValidation),
            label = stringResource(id = R.string.nameLabel),
            onValidate = {
                name.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldForm(
            value = cedula,
            onValueChange = {
                cedula = it
            },
            supportingText = stringResource(id = R.string.cedulaValidation),
            label = stringResource(id = R.string.cedulaLabel),
            onValidate = {
                cedula.isBlank()
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

        TextFieldForm(
            value = address,
            onValueChange = {
                address = it
            },
            supportingText = stringResource(id = R.string.addressValidation),
            label = stringResource(id = R.string.addressLabel),
            onValidate = {
                address.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldForm(
            value = phone,
            onValueChange = {
                phone = it
            },
            supportingText = stringResource(id = R.string.phoneValidation),
            label = stringResource(id = R.string.phoneLabel),
            onValidate = {
                phone.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextFieldForm(
            value = email,
            onValueChange = {
                email = it
            },
            supportingText = stringResource(id = R.string.emailValidation),
            label = stringResource(id = R.string.emailLabel),
            onValidate = {
                email.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        TextFieldForm(
            value = password,
            onValueChange = {
                password = it
            },
            supportingText = stringResource(id = R.string.passwordValidation),
            label = stringResource(id = R.string.passwordLabel),
            onValidate = {
                password.isBlank()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = "Fecha de nacimiento")
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

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onNavigateToHome()
            }
        ) {
            Text(text = stringResource(id = R.string.registerButton))
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
                                dateOfBirth = formattedDate
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