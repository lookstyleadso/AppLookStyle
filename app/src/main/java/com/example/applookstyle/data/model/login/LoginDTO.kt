package com.example.applookstyle.data.model.login

data class LoginDTO(
    val id: Int,
    val last_name: String,
    val name: String,
    val role: Int,
    val token: String
)