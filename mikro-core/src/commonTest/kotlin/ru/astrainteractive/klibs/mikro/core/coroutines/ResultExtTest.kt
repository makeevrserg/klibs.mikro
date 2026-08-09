package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.CancellationException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ResultExtTest {

    @Test
    fun GIVEN_success_WHEN_propagateCancellationException_THEN_returns_same_result() {
        val result = Result.success(42)
        assertEquals(result, result.propagateCancellationException())
    }

    @Test
    fun GIVEN_non_cancellation_failure_WHEN_propagate_THEN_returns_failure_without_throwing() {
        val error = IllegalStateException("boom")
        val result: Result<Int> = Result.failure(error)

        val propagated = result.propagateCancellationException()

        assertTrue(propagated.isFailure)
        assertEquals(error, propagated.exceptionOrNull())
    }

    @Test
    fun GIVEN_cancellation_failure_WHEN_propagate_THEN_rethrows_the_cancellation() {
        val cancellation = CancellationException("cancelled")
        val result: Result<Int> = Result.failure(cancellation)

        val thrown = assertFailsWith<CancellationException> {
            result.propagateCancellationException()
        }
        assertEquals("cancelled", thrown.message)
    }
}
