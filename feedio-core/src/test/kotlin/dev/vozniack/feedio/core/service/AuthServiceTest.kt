package dev.vozniack.feedio.core.service

import dev.vozniack.feedio.core.AbstractUnitTest
import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.LoginResponseDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.api.dto.SignupResponseDto
import dev.vozniack.feedio.core.domain.repository.UserRepository
import dev.vozniack.feedio.core.internal.exception.BadRequestException
import dev.vozniack.feedio.core.internal.exception.UnauthorizedException
import dev.vozniack.feedio.core.mock.mockLoginRequest
import dev.vozniack.feedio.core.mock.mockSignupRequest
import dev.vozniack.feedio.core.mock.mockUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

class AuthServiceTest @Autowired constructor(
    private val authService: AuthService,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : AbstractUnitTest() {

    @AfterEach
    fun `clean up`() {
        userRepository.deleteAll()
    }

    @Test
    fun `signup user`() {
        val request: SignupRequestDto = mockSignupRequest()

        val response: SignupResponseDto = authService.signup(request)
        assertTrue(response.success)
    }

    @Test
    fun `signup if user already exists`() {
        userRepository.save(mockUser().apply { password = passwordEncoder.encode("J0hn123!") })
        val request: SignupRequestDto = mockSignupRequest()

        assertThrows<BadRequestException> {
            authService.signup(request)
        }
    }

    @Test
    fun `login user`() {
        userRepository.save(mockUser().apply { password = passwordEncoder.encode("J0hn123!") })
        val request: LoginRequestDto = mockLoginRequest(password = "J0hn123!")

        val response: LoginResponseDto = authService.login(request)
        assertNotNull(response.token)
    }

    @Test
    fun `login if user is not active`() {
        userRepository.save(mockUser(active = false).apply { password = passwordEncoder.encode("J0hn123!") })
        val request: LoginRequestDto = mockLoginRequest(password = "J0hn123!")

        assertThrows<UnauthorizedException> {
            authService.login(request)
        }
    }

    @Test
    fun `login with incorrect password`() {
        userRepository.save(mockUser().apply { password = passwordEncoder.encode("J0hn123!") })
        val request: LoginRequestDto = mockLoginRequest(password = "BlaBla123!")

        assertThrows<UnauthorizedException> {
            authService.login(request)
        }
    }
}
