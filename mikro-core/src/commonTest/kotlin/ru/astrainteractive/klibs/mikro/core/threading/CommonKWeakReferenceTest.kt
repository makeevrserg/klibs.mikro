package ru.astrainteractive.klibs.mikro.core.threading

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonKWeakReferenceTest {
    class HeapClass(val value: Int = i++) {
        companion object {
            var i = 0
        }
    }

    @Test
    fun GIVEN_reusable_WHEN_have_prev_reference_THEN_not_created_again(): Unit = runTest {
        val reusable = weakRefReusable { HeapClass() }
        val a = reusable.value
        val b = reusable.value

        assertEquals(a, b)
    }.let { }
}
