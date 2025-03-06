package dev.vozniack.feedio.core.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RegexExtensionTest {

    @Test
    fun `match email regex`() {
        assertTrue("john.doe@feedio.com".matchesEmailRegex())
        assertFalse("john.doe@feedio".matchesEmailRegex())
        assertFalse("feedio.com".matchesEmailRegex())
    }

    @Test
    fun `match password regex`() {
        assertTrue("pasSword1!".matchesPasswordRegex())
        assertFalse("password1!".matchesPasswordRegex())
        assertFalse("pAssword!".matchesPasswordRegex())
        assertFalse("pAssword1".matchesPasswordRegex())
    }

    @Test
    fun `match language regex`() {
        assertTrue("en_EN".matchesLanguageRegex())
        assertFalse("en.EN".matchesLanguageRegex())
        assertFalse("en".matchesLanguageRegex())
    }
}
