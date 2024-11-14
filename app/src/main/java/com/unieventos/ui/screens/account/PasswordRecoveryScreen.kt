package com.unieventos.ui.screens.account

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
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.unieventos.ui.components.TextFieldForm

@Composable
fun PasswordRecoveryScreen(
    onNavigationBack: () -> Unit
){
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBarPasswordRecovery(
                onNavigationBack = onNavigationBack
            )
        }
    ){ padding ->
        PasswordRecoveryForm(
            padding = padding,
            context = context,
        )
    }
}

@Composable
fun PasswordRecoveryForm(
    padding: PaddingValues,
    context: Context

){
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextFieldForm(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            supportingText = stringResource(id = R.string.confirmPassword),
            label = stringResource(id = R.string.confirmPasswordLabel),
            onValidate = {
                confirmPassword.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
            }
        ) {
            Text(text = stringResource(id = R.string.saveButton))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarPasswordRecovery(
    onNavigationBack: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = "Cambiar contraseña")
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
