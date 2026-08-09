package ru.astrainteractive.klibs.mikro.core.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class CastExtTest {
    @Test
    fun GIVEN_value_of_matching_type_WHEN_tryCast_THEN_returns_value() {
        val any: Any = "text"
        assertEquals("text", any.tryCast<String>())
    }

    @Test
    fun GIVEN_value_of_other_type_WHEN_tryCast_THEN_returns_null() {
        val any: Any = 10
        assertNull(any.tryCast<String>())
    }

    @Test
    fun GIVEN_non_null_value_WHEN_orElse_THEN_returns_value_without_invoking_block() {
        var invoked = false
        val result = "value".orElse {
            invoked = true
            "fallback"
        }
        assertEquals("value", result)
        assertFalse(invoked)
    }

    @Test
    fun GIVEN_null_value_WHEN_orElse_THEN_returns_block_result() {
        val value: String? = null
        val result = value.orElse { "fallback" }
        assertEquals("fallback", result)
    }
}
