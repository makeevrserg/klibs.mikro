package ru.astrainteractive.klibs.mikro.core.threading

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KWeakReferenceTest {
    class HeapClass(val value: Int = i++) {
        companion object {
            var i = 0
        }
    }

    @Test
    fun GIVEN_reusable_WHEN_have_no_reference_THEN_should_be_garbage_collected(): Unit = runTest {
        val reusable = Reusable { HeapClass() }
        reusable.getValue()
        while (currentCoroutineContext().isActive) {
            delay(100L)
            if (reusable.orNull == null) break
        }
        assertTrue(reusable.orNull == null)
    }.let { }

    @Test
    fun GIVEN_reusable_WHEN_have_prev_reference_THEN_not_created_again(): Unit = runTest {
        val reusable = Reusable { HeapClass() }
        val a = reusable.getValue()
        val b = reusable.getValue()

        assertEquals(a, b)
    }.let { }
}
