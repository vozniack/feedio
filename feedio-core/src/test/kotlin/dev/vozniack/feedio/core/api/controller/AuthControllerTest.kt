package dev.vozniack.feedio.core.api.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.vozniack.feedio.core.AbstractWebMvcUnitTest
import dev.vozniack.feedio.core.api.dto.LoginRequestDto
import dev.vozniack.feedio.core.api.dto.LoginResponseDto
import dev.vozniack.feedio.core.api.dto.SignupRequestDto
import dev.vozniack.feedio.core.api.dto.SignupResponseDto
import dev.vozniack.feedio.core.domain.repository.UserRepository
import dev.vozniack.feedio.core.mock.mockLoginRequest
import dev.vozniack.feedio.core.mock.mockSignupRequest
import dev.vozniack.feedio.core.mock.mockUser
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.context.WebApplicationContext

class AuthControllerTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    context: WebApplicationContext
) : AbstractWebMvcUnitTest(context) {

    @AfterEach
    fun `clean up`() {
        userRepository.deleteAll()
    }

    @Test
    fun `signup user`() {
        val request: SignupRequestDto = mockSignupRequest()

        val response: SignupResponseDto = jacksonObjectMapper().readValue(
            mockMvc.perform(
                post("/api/auth/signup")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper().writeValueAsString(request))
            ).andExpect(status().isCreated).andReturn().response.contentAsString
        )

        assertTrue(response.success)
    }

    @Test
    fun `login user`() {
        userRepository.save(mockUser().apply { password = passwordEncoder.encode("J0hn123!") })

        val request: LoginRequestDto = mockLoginRequest(password = "J0hn123!")

        val response: LoginResponseDto = jacksonObjectMapper().readValue(
            mockMvc.perform(
                post("/api/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jacksonObjectMapper().writeValueAsString(request))
            ).andExpect(status().isOk).andReturn().response.contentAsString
        )

        assertNotNull(response.token)
    }
}
