package ru.astrainteractive.klibs.mikro.validation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidatorTest {
    @Test
    fun test() {
        val lengthKey = "LENGTH"
        val customWordKey = "ACC"
        val minLength = 6
        val validator = DefaultValidator<String, String> {
            validate(lengthKey) { it.length > minLength }
            validate(customWordKey) { it.contains(customWordKey) }
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
