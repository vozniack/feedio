package dev.vozniack.feedio.core.api.dto

data class SignupRequestDto(
    val email: String,
    val password: String,

    val firstName: String,
    val lastName: String,
    val language: String,
)

data class SignupResponseDto(
    val success: Boolean
)

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val token: String
)
