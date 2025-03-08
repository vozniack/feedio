package dev.vozniack.feedio.core.service

import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.LoginResponseDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.api.dto.SignupResponseDto
import dev.vozniack.feedio.core.api.extension.mapper.toUser
import dev.vozniack.feedio.core.domain.entity.User
import dev.vozniack.feedio.core.domain.repository.UserRepository
import dev.vozniack.feedio.core.internal.exception.BadRequestException
import dev.vozniack.feedio.core.internal.exception.UnauthorizedException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.util.Date
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    @Value("\${feedio.security.jwt.secret}") private val jwtSecret: String,
    @Value("\${feedio.security.jwt.expiration}") private val jwtExpiration: String
) {

    fun signup(request: SignupRequestDto): SignupResponseDto {
        userRepository.findByEmail(request.email)?.let {
            throw BadRequestException("User with email ${request.email} already exists")
        }

        val user: User = request.toUser().apply {
            active = true // #todo temporary solution, shouldn't be active without verification
            password = passwordEncoder.encode(request.password)
        }

        userRepository.save(user)

        return SignupResponseDto(true)
    }

    fun login(request: LoginRequestDto): LoginResponseDto = userRepository.findByEmail(request.email)
        ?.takeIf { it.active && passwordEncoder.matches(request.password, it.password) }
        ?.let { LoginResponseDto(token = buildToken(it)) }
        ?: throw UnauthorizedException("User ${request.email} has not been authorized")

    private fun buildToken(user: User): String = Jwts.builder()
        .setSubject(user.email)
        .signWith(Keys.hmacShaKeyFor(jwtSecret.toByteArray(StandardCharsets.UTF_8)))
        .setExpiration(Date(Date().time + jwtExpiration.toInt()))
        .compact()
}
