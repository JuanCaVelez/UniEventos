package com.unieventos.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun PasswordRecoveryScreen(

){

    val context = LocalContext.current

    Scaffold { padding ->
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
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )
        
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
