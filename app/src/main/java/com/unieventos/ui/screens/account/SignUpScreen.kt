package com.unieventos.ui.screens.account

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.unieventos.R
import com.unieventos.model.Role
import com.unieventos.model.User
import com.unieventos.ui.components.AppButton
import com.unieventos.ui.components.DropDownMenu
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onNavigationBack: () -> Unit,
    usersViewModel: UsersViewModel
){

    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Crear Usuario") },
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
    ) { padding ->
        SignUpForm(
            padding = PaddingValues(0.dp),
            context = context,
            onNavigationBack = onNavigationBack,
            usersViewModel = usersViewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpForm(
    padding: PaddingValues,
    context: Context,
    onNavigationBack: () -> Unit,
    usersViewModel: UsersViewModel
){

    val citys = listOf("Armenia", "Pereira", "Manizales")

    var name by rememberSaveable { mutableStateOf("") }
    var cedula by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }


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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        DropDownMenu(
            value = city,
            onValueChange = {
                city = it
            },
            items = citys,
            placeholder = "Seleccione la ciudad",
            label = stringResource(id = R.string.city)
        )

        Spacer(modifier = Modifier.height(10.dp))

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(20.dp))

        AppButton(
            onClick = {
                usersViewModel.createUser(User(
                    name = name,
                    id = cedula,
                    city = city,
                    address = address,
                    phone = phone,
                    role = Role.CLIENT,
                    email = email,
                    password = password
                ))
                Toast.makeText(context, context.getText(R.string.userCreate), Toast.LENGTH_SHORT).show()
            },
            text = stringResource(id = R.string.registerButton)
        )

        /*
        Button(
            onClick = {
                usersViewModel.createUser(User (
                    name = name,
                    id = cedula,
                    city = city,
                    address = address,
                    phone = phone,
                    role = Role.CLIENT,
                    email = email,
                    password = password

                ))
                Toast.makeText(context, context.getText(R.string.userCreate), Toast.LENGTH_SHORT).show()
                onNavigationBack()
            }
        ) {
            Text(text = stringResource(id = R.string.registerButton))
        }
         */
    }
}