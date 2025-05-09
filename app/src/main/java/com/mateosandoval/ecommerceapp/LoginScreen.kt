package com.mateosandoval.ecommerceapp

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(onClickRegister: () -> Unit, onSuccessfulLogin: () -> Unit) {

    //Estados de los inputs
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var LoginError by remember { mutableStateOf("") }
    var EmailError by remember { mutableStateOf("") }
    var PasswordError by remember { mutableStateOf("") }

    val activity = LocalView.current.context as Activity
    val auth = Firebase.auth

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())

                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_unab),
                contentDescription = "Logo Unab",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Iniciar Sesión",
                fontSize = 34.sp,
                color = Color(0xFFFF9900),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = inputEmail,
                onValueChange = {
                    inputEmail = it
                },
                label = {
                    Text(
                        text = "Email",
                        color = Color(0xFFFF9900)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon",
                        tint = Color(0xFFFF9900)
                    )
                },
                shape = RoundedCornerShape(13.dp),
                supportingText = {
                    if (EmailError.isNotEmpty()) {
                        Text(
                            text = EmailError,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                )

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = inputPassword,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    inputPassword = it
                },
                label = {
                    Text(
                        text = "Contraseña",
                        color = Color(0xFFFF9900)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon",
                        tint = Color(0xFFFF9900)
                    )
                },
                shape = RoundedCornerShape(13.dp),
                supportingText = {
                    if (PasswordError.isNotEmpty()) {
                        Text(
                            text = PasswordError,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,

                    )

            )


            Spacer(modifier = Modifier.height(24.dp))
            if (LoginError.isNotEmpty()) {
                Text(
                    LoginError,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)

                )


            }


            Button(
                onClick = {

                    val isValidEmail = validateEmail(inputEmail).first
                    val isValidPassword = validatePassword(inputPassword).first
                    EmailError = validateEmail(inputEmail).second
                    PasswordError = validateEmail(inputPassword).second

                    if (isValidEmail && isValidPassword) {

                        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                            .addOnCompleteListener() { task ->

                                if (task.isSuccessful) {
                                    onSuccessfulLogin()
                                } else {
                                    LoginError = when (task.exception) {
                                        is FirebaseAuthInvalidUserException -> "El usuario no existe"
                                        is FirebaseAuthInvalidCredentialsException -> "Correo o contraseña incorrecta"
                                        else -> "Error al iniciar sesión. Inténtalo de nuevo"

                                    }
                                }
                            }

                    } else {

                    }


                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(13.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9900)
                )
            ) {
                Text(text = "Iniciar Sesión", color = Color.White, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(1.dp))

            TextButton(onClick = onClickRegister) {
                Text(
                    text = "No tienes una cuenta? Regístrate",
                    color = Color(0xFFFF9900),
                )
            }


        }




    }

}



