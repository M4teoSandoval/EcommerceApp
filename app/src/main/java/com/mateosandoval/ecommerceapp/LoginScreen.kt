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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun LoginScreen(navController: NavController) {

    //Estados de los inputs
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    val activity = LocalView.current.context as Activity

    var isEmaiValid by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
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
                shape = RoundedCornerShape(13.dp)
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
                shape = RoundedCornerShape(13.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val auth = Firebase.auth

                    auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                        .addOnCompleteListener() { task ->

                            if (task.isSuccessful) {
                                navController.navigate("home")
                            } else {
                                Log.i("Login", "Login failed")
                            }
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

            TextButton(onClick = {
                navController.navigate("register")

            }) {
                Text(
                    text = "No tienes una cuenta? Regístrate",
                    color = Color(0xFFFF9900),
                )
            }


        }

    }

}

fun validateEmail(email: String): Pair<Boolean, String> {
    return when {

        email.isEmpty() -> return Pair(false, "El email no puede estar vacío")
        !email.endsWith("@unab.edu.co") -> return Pair(
            false,
            "El email debe terminar con @unab.edu.co"
        )
        else -> return Pair(true, "")

    }
}