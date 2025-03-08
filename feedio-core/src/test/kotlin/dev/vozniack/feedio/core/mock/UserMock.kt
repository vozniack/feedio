package dev.vozniack.feedio.core.mock

import dev.vozniack.feedio.core.domain.entity.User
import java.time.LocalDate

fun mockUser(
    email: String = "john.doe@feedio.com",
    password: String = "J0hn123!",
    firstName: String = "John",
    lastName: String = "Doe",
    dateOfBirth: LocalDate = LocalDate.now().minusYears(24),
    language: String = "en_EN",
    active: Boolean = true
): User = User(
    email = email,
    password = password,
    firstName = firstName,
    lastName = lastName,
    dateOfBirth = dateOfBirth,
    language = language,
    active = active
)
