package com.mateosandoval.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mateosandoval.ecommerceapp.ui.theme.EcommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceAppTheme {

                val myNavController = rememberNavController()
                var myStartDestination: String = "login"

                val auth = Firebase.auth
                val user = auth.currentUser

                if (user != null) {
                    myStartDestination = "home"
                }else{
                    myStartDestination = "login"
                }

                NavHost(
                    navController = myNavController,
                    startDestination = myStartDestination,
                ) {
                    composable("login") {
                        LoginScreen(onClickRegister = {
                            myNavController.navigate("register")
                        }, onSuccessfulLogin = {
                            myNavController.navigate("home") {
                                popUpTo("login") {
                                    inclusive = true
                                }
                            }
                        })
                    }
                    composable("register") {
                        RegisterScreen(onClickBack = {
                            myNavController.popBackStack()
                        }, onSuccessfulRegister = {
                            myNavController.navigate("home") {
                                popUpTo(0)
                            }
                        })
                    }
                    composable("home") {
                        HomeScreen(onClickLogOut = {
                            myNavController.navigate("login") {
                                popUpTo(0)
                            }
                        })
                    }


                }
            }
        }
    }

}