package com.unieventos.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.unieventos.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    usersViewModel: UsersViewModel,
    onNavigateToPasswordRecovery: () -> Unit,
    onNavigationBack: () -> Unit
){
    val context = LocalContext.current

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text(text = "Editar Perfil")},
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
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(1.dp))

            EditProfileForm(
                padding = PaddingValues(0.dp),
                context = context,
                usersViewModel = usersViewModel,
                onNavigateToPasswordRecovery = onNavigateToPasswordRecovery
            )
        }
    }
}

@Composable
fun EditProfileForm(
    padding: PaddingValues,
    context: Context,
    usersViewModel: UsersViewModel,
    onNavigateToPasswordRecovery: () -> Unit

) {

    val citys = listOf("Armenia", "Cali", "Medellin")

    val user by usersViewModel.currentUser.collectAsState()

    if(user != null){
        var name by rememberSaveable { mutableStateOf(user?.name?: "") }
        var cedula by rememberSaveable { mutableStateOf(user?.id?: "") }
        var city by rememberSaveable { mutableStateOf(user?.city?: "") }
        var address by rememberSaveable { mutableStateOf(user?.address?: "") }
        var phone by rememberSaveable { mutableStateOf(user?.phone?: "") }
        var email by rememberSaveable { mutableStateOf(user?.email?: "") }
        var password by rememberSaveable { mutableStateOf(user?.password?: "") }

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
                onValidate = { false},
                KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            TextFieldForm(
                value = cedula,
                onValueChange = {
                    cedula = it
                },
                supportingText = stringResource(id = R.string.cedulaValidation),
                label = stringResource(id = R.string.cedulaLabel),
                onValidate = {false},
                KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            DropDownMenu(
                value = city,
                onValueChange = {
                    city = it
                },
                items = citys,
                placeholder = "Seleccione la ciudad"
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

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                }
            ) {
                Text(text = stringResource(id = R.string.saveButton))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { onNavigateToPasswordRecovery()}
            ) {
                Text(text = stringResource(id = R.string.changePassword))
            }
        }
    }
}