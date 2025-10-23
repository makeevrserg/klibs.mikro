package ru.astrainteractive.klibs.mikro.core.threading

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class MutableLazyTest {
    @Test
    fun GIVEN_null_in_lazy_WHEN_initialize_THEN_ok() {
        val lazyValue by mutableLazy { null }
        assertNull(lazyValue)
    }

    @Test
    fun GIVEN_non_null_in_lazy_WHEN_initialize_THEN_ok() {
        val lazyValue by mutableLazy { 10 }
        assertEquals(10, lazyValue)
    }

    @Test
    fun GIVEN_any_in_lazy_WHEN_change_THEN_changet() {
        mutableLazy { 10 }.let { mutableLazy ->
            var lazyValue by mutableLazy
            assertEquals(10, lazyValue)
            lazyValue = 11
            assertEquals(11, lazyValue)
        }

        mutableLazy<Int?> { null }.let { mutableLazy ->
            var lazyValue by mutableLazy
            assertNull(lazyValue)
            lazyValue = 10
            assertEquals(10, lazyValue)
        }
    }
}
