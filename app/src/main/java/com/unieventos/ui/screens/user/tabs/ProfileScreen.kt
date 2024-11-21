package com.unieventos.ui.screens.user.tabs

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.unieventos.ui.components.AppButton
import com.unieventos.ui.components.DropDownMenu
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    usersViewModel: UsersViewModel,
    onNavigateToPasswordRecovery: () -> Unit,
    onNavigationBack: () -> Unit
) {
    val context = LocalContext.current

    Scaffold(
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            EditProfileUserForm(
                padding = PaddingValues(0.dp),
                context = context,
                usersViewModel = usersViewModel,
                onNavigateToPasswordRecovery = onNavigateToPasswordRecovery,
                onNavigationBack = onNavigationBack
            )
        }
    }
}

@Composable
fun EditProfileUserForm(
    padding: PaddingValues,
    context: Context,
    usersViewModel: UsersViewModel,
    onNavigateToPasswordRecovery: () -> Unit,
    onNavigationBack: () -> Unit
) {
    val citys = listOf("Armenia", "Cali", "Medellin")
    val user by usersViewModel.currentUser.collectAsState(initial = null)

    if (user == null) {
        var name by rememberSaveable { mutableStateOf(user?.name ?: "") }
        var cedula by rememberSaveable { mutableStateOf(user?.id ?: "") }
        var city by rememberSaveable { mutableStateOf(user?.city ?: "") }
        var address by rememberSaveable { mutableStateOf(user?.address ?: "") }
        var phone by rememberSaveable { mutableStateOf(user?.phone ?: "") }
        var email by rememberSaveable { mutableStateOf(user?.email ?: "") }
        var password by rememberSaveable { mutableStateOf(user?.password ?: "") }

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                TextFieldForm(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    supportingText = stringResource(id = R.string.nameValidation),
                    label = stringResource(id = R.string.nameLabel),
                    onValidate = { false },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                TextFieldForm(
                    modifier = Modifier.fillMaxWidth(),
                    value = cedula,
                    onValueChange = { cedula = it },
                    supportingText = stringResource(id = R.string.cedulaValidation),
                    label = stringResource(id = R.string.cedulaLabel),
                    onValidate = { false },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                DropDownMenu(
                    value = city,
                    onValueChange = { city = it },
                    items = citys,
                    placeholder = "Seleccione la ciudad",
                    label = stringResource(id = R.string.city)
                )

                TextFieldForm(
                    modifier = Modifier.fillMaxWidth(),
                    value = address,
                    onValueChange = { address = it },
                    supportingText = stringResource(id = R.string.addressValidation),
                    label = stringResource(id = R.string.addressLabel),
                    onValidate = { address.isBlank() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                TextFieldForm(
                    modifier = Modifier.fillMaxWidth(),
                    value = phone,
                    onValueChange = { phone = it },
                    supportingText = stringResource(id = R.string.phoneValidation),
                    label = stringResource(id = R.string.phoneLabel),
                    onValidate = { phone.isBlank() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextFieldForm(
                    modifier = Modifier.fillMaxWidth(),
                    value = email,
                    onValueChange = { email = it },
                    supportingText = stringResource(id = R.string.emailValidation),
                    label = stringResource(id = R.string.emailLabel),
                    onValidate = { email.isBlank() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                TextFieldForm(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    supportingText = stringResource(id = R.string.passwordValidation),
                    label = stringResource(id = R.string.passwordLabel),
                    onValidate = { password.isBlank() },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        AppButton(
                            onClick = {

                            },
                            text = stringResource(id = R.string.saveButton)
                        )

                        AppButton(

                            onClick = {
                                onNavigateToPasswordRecovery()
                            },
                            text = stringResource(id = R.string.changePassword)
                        )
                    }
                }
            }
        }
    }
}