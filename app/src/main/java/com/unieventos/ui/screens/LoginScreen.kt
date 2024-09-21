package com.unieventos.ui.screens

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Login
import androidx.compose.material3.Icon
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
import com.unieventos.ui.components.TextFieldForm

@Composable
fun LoginScreen(
    onNavigateToSignUp: () -> Unit,
    onNavigateHome: () -> Unit
){

    val context = LocalContext.current

    Scaffold { padding ->
        LoginForm(
            padding = padding,
            context = context,
            onNavigateToSignUp = onNavigateToSignUp,
            onNavigateHome = onNavigateHome
        )
    }
}

@Composable
fun LoginForm(
    padding: PaddingValues,
    context: Context,
    onNavigateToSignUp: () -> Unit,
    onNavigateHome: () -> Unit
){

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
            value = email,
            onValueChange = {
                email = it
            },
            supportingText = stringResource(id = R.string.emailValidation),
            label = stringResource(id = R.string.emailLabel),
            onValidate = {
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextFieldForm(
            value = password,
            onValueChange = {
                password = it
            },
                supportingText = stringResource(id = R.string.passwordValidation),
            label = stringResource(id = R.string.passwordLabel),
            onValidate = {
                password.length < 6
            },
            KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        val welcome = stringResource(id = R.string.welcomeMessage)
        val loginValidation = stringResource(id = R.string.loginValidation)

        Button(
            enabled = email.isNotEmpty() && password.isNotEmpty(),
            onClick = {
                if(email == "carlos@email.com" && password == "123456"){
                    onNavigateHome()
                }else{
                    Toast.makeText(context, loginValidation, Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text(text = stringResource(id = R.string.loginButton))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            onNavigateToSignUp()
        }
        ) {
            Icon(
                imageVector = Icons.Rounded.Login,
                contentDescription = "Icono registro"
            )
            Text(text = stringResource(id = R.string.registerButton))
        }
    }
}

