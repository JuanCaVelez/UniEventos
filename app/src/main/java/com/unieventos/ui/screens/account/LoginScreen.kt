package com.unieventos.ui.screens.account

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unieventos.R
import com.unieventos.model.Role
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.utils.SharePrefencesUtils
import com.unieventos.viewmodel.UsersViewModel

@Composable
fun LoginScreen(
    onNavigateToSignUp: () -> Unit,
    onNavigateHome: (Role) -> Unit,
    usersViewModel: UsersViewModel
){

    val context = LocalContext.current

    Scaffold { padding ->
        LoginForm(
            usersViewModel = usersViewModel,
            padding = padding,
            context = context,
            onNavigateToSignUp = onNavigateToSignUp,
            onNavigateHome = onNavigateHome,
        )
    }
}



@Composable
fun LoginForm(
    padding: PaddingValues,
    context: Context,
    onNavigateToSignUp: () -> Unit,
    onNavigateHome: (Role) -> Unit,
    usersViewModel: UsersViewModel
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top // Usamos SpaceBetween para distribuir mejor
    ) {

        Spacer(modifier = Modifier.height(300.dp))
        // Parte superior: Espacio para los campos de texto
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(5.dp))

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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isPassword = true
            )
        }

        Spacer(modifier = Modifier.height(200.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 50.dp)
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            LoginButton(
                email = email,
                password = password,
                usersViewModel = usersViewModel,
                context = context,
                onNavigateHome = onNavigateHome
            )

            Spacer(modifier = Modifier.height(40.dp))

            val annotatedText = buildAnnotatedString {
                append("No tienes cuenta? ")
                pushStringAnnotation(tag = "signup", annotation = "signup_screen")
                withStyle(
                    style = androidx.compose.ui.text.SpanStyle(
                        color = androidx.compose.ui.graphics.Color(0xFF42d322),
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append("Regístrate")
                }
                pop()
            }

            Text(
                text = annotatedText,
                modifier = Modifier.clickable {
                    onNavigateToSignUp()
                }
            )
        }
    }
}

@Composable
fun LoginButton(
    email: String,
    password: String,
    usersViewModel: UsersViewModel,
    context: Context,
    onNavigateHome: (Role) -> Unit
){
    Button(
        enabled = email.isNotEmpty() && password.isNotEmpty(),
        modifier = Modifier
            .height(50.dp)
            .width(160.dp),
        onClick = {
            val user = usersViewModel.login(email, password)

            if (user != null){
                SharePrefencesUtils.savePreferences(context, user.id, user.role)
                usersViewModel.loadCurrentUser(user)
                onNavigateHome(user.role)

                val welcomeMessage = context.getString(R.string.welcomeMessage) + " " + user.name
                Toast.makeText(context, welcomeMessage, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, R.string.loginValidation , Toast.LENGTH_SHORT).show()
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF42d322),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.loginButton),
            fontSize = 18.sp
        )
    }
}
