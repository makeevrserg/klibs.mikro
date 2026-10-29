package ru.astrainteractive.klibs.mikro.core.coroutines.util

import kotlinx.coroutines.test.runTest
import ru.astrainteractive.klibs.mikro.core.coroutines.api.Suspender
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class SuspendExtTest {

    @Test
    fun GIVEN_condition_true_after_several_checks_WHEN_await_for_completion_THEN_returns_once_satisfied() {
        runTest {
            var counter = 0
            awaitForCompletion(delayDuration = 10.milliseconds) {
                counter++
                counter >= 3
            }
            assertEquals(3, counter)
        }
    }

    @Test
    fun GIVEN_condition_already_true_WHEN_await_for_completion_THEN_checks_only_once() {
        runTest {
            var checks = 0
            awaitForCompletion(delayDuration = Duration.ZERO) {
                checks++
                true
            }
            assertEquals(1, checks)
        }
    }

    @Test
    fun GIVEN_suspender_that_becomes_free_WHEN_await_not_suspended_THEN_returns_when_not_suspended() {
        runTest {
            var remainingSuspendedChecks = 2
            val suspender = object : Suspender {
                override fun isSuspended(): Boolean = remainingSuspendedChecks-- > 0
                override fun setSuspended() = Unit
            }
            suspender.awaitNotSuspended(delay = 10.milliseconds)
            assertTrue(remainingSuspendedChecks < 0)
        }
    }
}
