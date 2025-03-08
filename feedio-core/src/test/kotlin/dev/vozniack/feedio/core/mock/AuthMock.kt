package dev.vozniack.feedio.core.mock

import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.util.toStringLocalDate
import java.time.LocalDate

fun mockSignupRequest(
    email: String = "john.doe@feedio.com",
    password: String = "J0hn123!",
    firstName: String = "John",
    lastName: String = "Doe",
    dateOfBirth: String = LocalDate.now().minusYears(24).toStringLocalDate(),
    language: String = "en_EN"
): SignupRequestDto = SignupRequestDto(
    email = email,
    password = password,
    firstName = firstName,
    lastName = lastName,
    dateOfBirth = dateOfBirth,
    language = language
)

fun mockLoginRequest(
    email: String = "john.doe@feedio.com",
    password: String = "J0hn123!",
): LoginRequestDto = LoginRequestDto(email = email, password = password)
