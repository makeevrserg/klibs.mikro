@file:Suppress("FunctionNaming")

package ru.astrainteractive.klibs.mikro.core.threading

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class NonJsKWeakReferenceTest {
    class HeapClass(val value: Int = i++) {
        companion object {
            var i = 0
        }
    }

    @Test
    fun GIVEN_reusable_WHEN_have_no_reference_THEN_should_be_garbage_collected(): Unit = runTest {
        val reusable = weakRefReusable { HeapClass() }
        reusable.value
        while (currentCoroutineContext().isActive) {
            delay(100L)
            if (reusable.orNull == null) break
        }
        println("Asserted!")
        assertTrue(reusable.orNull == null)
    }.let { }
}
