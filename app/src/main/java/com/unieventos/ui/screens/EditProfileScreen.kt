package com.unieventos.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import com.unieventos.R
import com.unieventos.ui.components.TextFieldForm

@Composable
fun EditProfileScreen(

){
    val context = LocalContext.current

    Scaffold { padding ->
        EditProfileForm(
            padding = padding,
            context = context,
        )
    }
}

@Composable
fun EditProfileForm(
    padding: PaddingValues,
    context: Context
){
    var name by rememberSaveable { mutableStateOf("") }
    var cedula by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )
        
        Button(
            onClick = {
                
            }
        ) {
            Text(text = stringResource(id = R.string.saveButton))
        }
    }
}