package dev.vozniack.feedio.core.api.controller

import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.LoginResponseDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.api.dto.SignupResponseDto
import dev.vozniack.feedio.core.api.extension.validator.validate
import dev.vozniack.feedio.core.internal.logging.KLogging
import dev.vozniack.feedio.core.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody request: SignupRequestDto): SignupResponseDto {
        request.validate().also {
            logger.debug { "Signing up user ${request.email}" }
        }

        return authService.signup(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequestDto): LoginResponseDto {
        request.validate().also {
            logger.debug { "Logging in user ${request.email}" }
        }

        return authService.login(request)
    }

    companion object : KLogging()
}
