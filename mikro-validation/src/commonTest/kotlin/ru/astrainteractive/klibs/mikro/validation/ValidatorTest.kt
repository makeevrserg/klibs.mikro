package ru.astrainteractive.klibs.mikro.validation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ValidatorTest {
    @Test
    fun test() {
        val LENGTH_KEY = "LENGTH"
        val CUSTOM_WORD_KEY = "ACC"
        val MIN_LENGTH = 6
        val validator = DefaultValidator<String, String> {
            validate { it.length > MIN_LENGTH } otherwise LENGTH_KEY
            validate { it.contains(CUSTOM_WORD_KEY) } otherwise CUSTOM_WORD_KEY
        }
        validator.validate("More then $MIN_LENGTH characters").let { result ->
            assertTrue(result.isFailure)
            assertEquals(CUSTOM_WORD_KEY, result.violationOrNull)
        }
        validator.validate(CUSTOM_WORD_KEY).let { result ->
            assertTrue(result.isFailure)
            assertEquals(LENGTH_KEY, result.violationOrNull)
        }
        validator.validate("").let { result ->
            assertTrue(result.isFailure)
            assertEquals(LENGTH_KEY, result.violationOrNull)
        }
        assertTrue {
            validator.validate("Contains $CUSTOM_WORD_KEY, more than $MIN_LENGTH character").isSuccess
        }
    }

}


