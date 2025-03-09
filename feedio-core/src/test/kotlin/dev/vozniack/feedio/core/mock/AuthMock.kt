package dev.vozniack.feedio.core.mock

import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto

fun mockSignupRequest(
    email: String = "john.doe@feedio.com",
    password: String = "J0hn123!",
    firstName: String = "John",
    lastName: String = "Doe",
    language: String = "en_EN"
): SignupRequestDto = SignupRequestDto(
    email = email,
    password = password,
    firstName = firstName,
    lastName = lastName,
    language = language
)

fun mockLoginRequest(
    email: String = "john.doe@feedio.com",
    password: String = "J0hn123!",
): LoginRequestDto = LoginRequestDto(email = email, password = password)
