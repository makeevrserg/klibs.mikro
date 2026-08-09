package ru.astrainteractive.klibs.mikro.validation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MailValidatorTest {
    private val validator = MailValidator()

    @Test
    fun GIVEN_text_containing_at_symbol_WHEN_validate_THEN_success() {
        assertTrue(validator.validate("user@example.com").isSuccess)
    }

    @Test
    fun GIVEN_text_without_at_symbol_WHEN_validate_THEN_failure_with_message() {
        val result = validator.validate("plain-text")
        assertTrue(result.isFailure)
        assertEquals("Not contains @ symbol", result.violationOrNull)
    }
}
