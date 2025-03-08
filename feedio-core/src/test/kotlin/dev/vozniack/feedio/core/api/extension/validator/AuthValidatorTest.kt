package dev.vozniack.feedio.core.api.extension.validator

import dev.vozniack.feedio.core.internal.exception.BadRequestException
import dev.vozniack.feedio.core.mock.mockLoginRequest
import dev.vozniack.feedio.core.mock.mockSignupRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AuthValidatorTest {

    @Test
    fun `validate signup request`() {
        mockSignupRequest().validate()

        // missing email
        assertThrows<BadRequestException> {
            mockSignupRequest(email = "").validate()
        }

        // missing password
        assertThrows<BadRequestException> {
            mockSignupRequest(password = "").validate()
        }

        // missing first name
        assertThrows<BadRequestException> {
            mockSignupRequest(firstName = "").validate()
        }

        // missing last name
        assertThrows<BadRequestException> {
            mockSignupRequest(lastName = "").validate()
        }

        // missing date of birth
        assertThrows<BadRequestException> {
            mockSignupRequest(dateOfBirth = "").validate()
        }

        // missing language
        assertThrows<BadRequestException> {
            mockSignupRequest(language = "").validate()
        }

        // email without @ character
        assertThrows<BadRequestException> {
            mockSignupRequest(email = "john.doefeedio.com").validate()
        }

        // email without domain
        assertThrows<BadRequestException> {
            mockSignupRequest(email = "john.doe@feedio").validate()
        }

        // password without capital letter
        assertThrows<BadRequestException> {
            mockSignupRequest(password = "j0hn123!").validate()
        }

        // password without number
        assertThrows<BadRequestException> {
            mockSignupRequest(password = "Johnn!").validate()
        }

        // password without special character
        assertThrows<BadRequestException> {
            mockSignupRequest(password = "J0hn123").validate()
        }

        // date of birth in not correct ISO form
        assertThrows<BadRequestException> {
            mockSignupRequest(dateOfBirth = "01-01-1990").validate()
        }

        // language in not correct ISO form
        assertThrows<BadRequestException> {
            mockSignupRequest(language = "EN_en").validate()
        }
    }

    @Test
    fun `validate login request`() {
        mockLoginRequest().validate()

        // missing email
        assertThrows<BadRequestException> {
            mockLoginRequest(email = "").validate()
        }

        // missing password
        assertThrows<BadRequestException> {
            mockLoginRequest(password = "").validate()
        }
    }
}
