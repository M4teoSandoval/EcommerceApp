package com.mateosandoval.ecommerceapp

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(onClickBack: () -> Unit, onSuccessfulRegister: () -> Unit) {

    val auth = Firebase.auth
    val activity = LocalView.current.context as Activity

    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var inputName by remember { mutableStateOf("") }
    var inputConfirmPassword by remember { mutableStateOf("") }


    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }

    var registerError by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "", color = Color(0xFFFF9900))
                },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFFFF9900)
                        )
                    }

                }

            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                colorFilter = ColorFilter.tint(Color(0xFFFF9900))
            )
            Spacer(modifier = Modifier.height(0.dp))
            Text(
                text = "Registrarse",
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
                    if (emailError.isNotEmpty()) {
                        Text(
                            text = emailError,
                            color = Color.Red
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = inputName,
                onValueChange = {
                    inputName = it
                },
                label = {
                    Text(
                        text = "Nombre Completo",
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
                    if (nameError.isNotEmpty()) {
                        Text(
                            text = nameError,
                            color = Color.Red
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = inputPassword,
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
                    if (passwordError.isNotEmpty()) {
                        Text(
                            text = passwordError,
                            color = Color.Red
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = inputConfirmPassword,
                onValueChange = {
                    inputConfirmPassword = it
                },
                label = {
                    Text(
                        text = "Confirmar Contraseña",
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
                    if (confirmPasswordError.isNotEmpty()) {
                        Text(
                            text = confirmPasswordError,
                            color = Color.Red
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))
            if (registerError.isNotEmpty()) {
                Text(
                    registerError,
                    color = Color.Red
                )

                Button(
                    onClick = {
                        val isValidEmail = validateEmail(inputEmail).first
                        val isValidPassword = validatePassword(inputPassword).first
                        val isValidName = validateName(inputName).first
                        val isValidConfirmPassword =
                            validateConfirmPassword(inputPassword, inputConfirmPassword).first


                        emailError = validateEmail(inputEmail).second
                        passwordError = validatePassword(inputPassword).second
                        nameError = validateName(inputName).second
                        confirmPasswordError =
                            validateConfirmPassword(inputPassword, inputConfirmPassword).second

                        if (isValidEmail && isValidPassword && isValidName && isValidConfirmPassword) {
                            auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                                .addOnCompleteListener(activity) { task ->
                                    if (task.isSuccessful) {
                                        onSuccessfulRegister()
                                    } else {
                                        registerError = when (task.isSuccessful) {
                                            is FirebaseAuthInvalidCredentialsException -> "Correo o contraseña incorrecta"
                                            is FirebaseAuthUserCollisionException -> "El usuario ya existe"
                                            else -> "Error al iniciar sesión. Inténtalo de nuevo"

                                        }

                                    }

                                }
                        } else {
                            registerError = "Error al registrarse"

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
                    Text(text = "Registrarse", color = Color.White, fontSize = 18.sp)
                }

            }
        }
    }
}


