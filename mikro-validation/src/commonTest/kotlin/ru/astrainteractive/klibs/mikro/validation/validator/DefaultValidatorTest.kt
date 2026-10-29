package ru.astrainteractive.klibs.mikro.validation.validator

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DefaultValidatorTest {

    @Test
    fun GIVEN_all_validations_pass_WHEN_validate_THEN_success() {
        val validator = DefaultValidator<String, String> {
            validate("too short") { value -> value.length > 3 }
            validate("missing dash") { value -> value.contains("-") }
        }
        assertTrue(validator.validate("ab-cd").isSuccess)
    }

    @Test
    fun GIVEN_no_validations_WHEN_validate_THEN_success() {
        val validator = DefaultValidator<String, String> { }
        assertTrue(validator.validate("anything").isSuccess)
    }

    @Test
    fun GIVEN_multiple_failing_validations_WHEN_validate_THEN_returns_first_in_declaration_order() {
        val validator = DefaultValidator<String, String> {
            validate("first") { _ -> false }
            validate("second") { _ -> false }
        }
        assertEquals("first", validator.validate("value").violationOrNull)
    }

    @Test
    fun GIVEN_earlier_validation_passes_WHEN_validate_THEN_returns_first_failing_violation() {
        val validator = DefaultValidator<String, String> {
            validate("too short") { value -> value.length > 3 }
            validate("missing dash") { value -> value.contains("-") }
        }
        assertEquals("missing dash", validator.validate("abcd").violationOrNull)
    }

    @Test
    fun GIVEN_function_based_otherwise_WHEN_validation_fails_THEN_violation_built_from_value() {
        val validator = DefaultValidator<String, String> {
            validate(otherwise = { value -> "invalid: $value" }, String::isEmpty)
        }
        assertEquals("invalid: hello", validator.validate("hello").violationOrNull)
    }

    @Test
    fun GIVEN_multiple_constraints_WHEN_validate_THEN_returns_expected_violations() {
        val lengthKey = "LENGTH"
        val customWordKey = "ACC"
        val minLength = 6
        val validator = DefaultValidator<String, String> {
            validate(lengthKey) { value -> value.length > minLength }
            validate(customWordKey) { value -> value.contains(customWordKey) }
        }
        validator.validate("More then $minLength characters").let { result ->
            assertTrue(result.isFailure)
            assertEquals(customWordKey, result.violationOrNull)
        }
        validator.validate(customWordKey).let { result ->
            assertTrue(result.isFailure)
            assertEquals(lengthKey, result.violationOrNull)
        }
        validator.validate("").let { result ->
            assertTrue(result.isFailure)
            assertEquals(lengthKey, result.violationOrNull)
        }
        assertTrue {
            validator.validate("Contains $customWordKey, more than $minLength character").isSuccess
        }
    }
}
