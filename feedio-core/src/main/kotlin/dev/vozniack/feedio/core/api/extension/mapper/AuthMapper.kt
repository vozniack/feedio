package dev.vozniack.feedio.core.api.extension.mapper

import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.domain.entity.User

fun SignupRequestDto.toUser(): User = User(
    email = email,
    firstName = firstName,
    lastName = lastName,
    language = language
)
