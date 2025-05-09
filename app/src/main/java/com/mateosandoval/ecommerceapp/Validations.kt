package com.mateosandoval.ecommerceapp

import android.util.Patterns

fun validateEmail(email: String): Pair<Boolean, String> {
    return when{
        email.isEmpty() -> Pair(false, "Email cannot be empty")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false, "Invalid email format")
        !email.endsWith("@gmail.com") -> Pair(false, "Email no corporativo")
        else -> Pair(true, "")

    }
}


fun validatePassword(password: String): Pair<Boolean, String> {
    return when {
        password.isEmpty() -> Pair(false, "Password cannot be empty")
        password.length < 8 -> Pair(false, "Password must be at least 8 characters long")
        !password.any { it.isDigit() } -> Pair(false, "Password must contain at least one digit")
        else -> Pair(true, "")
    }
}

fun validateName(name: String): Pair<Boolean, String> {
    return when {
        name.isEmpty() -> Pair(false, "Name cannot be empty")
        name.length < 3 -> Pair(false, "Name must be at least 3 characters long")
        !name.all { it.isLetter() } -> Pair(false, "Name must contain only letters")

        else -> Pair(true, "")
    }

}

fun validateConfirmPassword(password: String, confirmPassword: String): Pair<Boolean, String> {
    return when {
        password != confirmPassword -> Pair(false, "Passwords do not match")
        confirmPassword.isEmpty() -> Pair(false, "Confirm password cannot be empty")
        else -> Pair(true, "")
    }

}