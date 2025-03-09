package dev.vozniack.feedio.core.api.extension.validator

import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.internal.exception.BadRequestException
import dev.vozniack.feedio.core.util.matchesEmailRegex
import dev.vozniack.feedio.core.util.matchesLanguageRegex
import dev.vozniack.feedio.core.util.matchesPasswordRegex
import dev.vozniack.feedio.core.util.throwIfTrue

fun SignupRequestDto.validate() {
    throwIfTrue(BadRequestException("Email can't be empty")) { email.isEmpty() }
    throwIfTrue(BadRequestException("Password can't be empty")) { password.isEmpty() }

    throwIfTrue(BadRequestException("First name can't be empty")) { firstName.isEmpty() }
    throwIfTrue(BadRequestException("Last name can't be empty")) { lastName.isEmpty() }
    throwIfTrue(BadRequestException("Language can't be empty")) { language.isEmpty() }

    throwIfTrue(BadRequestException("Email must be a valid address")) {
        !email.matchesEmailRegex()
    }

    throwIfTrue(BadRequestException("Password must be at least 6 characters length and contain capital letter, number and special character")) {
        !password.matchesPasswordRegex()
    }

    throwIfTrue(BadRequestException("Language must be in ISO form, e.g. en_US")) {
        !language.matchesLanguageRegex()
    }
}

fun LoginRequestDto.validate() {
    throwIfTrue(BadRequestException("Email can't be empty")) { email.isEmpty() }
    throwIfTrue(BadRequestException("Password can't be empty")) { password.isEmpty() }
}
