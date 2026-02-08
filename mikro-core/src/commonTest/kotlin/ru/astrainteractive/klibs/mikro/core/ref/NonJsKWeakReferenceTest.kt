package ru.astrainteractive.klibs.mikro.core.ref

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.reuse.weakRefReusable
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

        run { reusable.value }

        while (currentCoroutineContext().isActive) {
            withContext(Dispatchers.Default) { delay(10L) }
            ByteArray(64 * 1024 * 1024)
            if (reusable.orNull == null) break
        }

        assertTrue(reusable.orNull == null)
    }.let { }
}
