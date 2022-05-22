package com.example.vhaapp.model

data class LoginSuccessfully(
    val gender: String,
    val id: Int,
    val jwt: String,
    val role: String,
    val status: String
)