package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class ClockSuspenderTest {
    @OptIn(ExperimentalTime::class)
    private class MutableClock(var instant: Instant) : Clock {
        override fun now(): Instant = instant
    }

    private val start = Instant.fromEpochSeconds(1000)

    private fun suspender(clock: MutableClock, duration: Duration = 30.seconds): ClockSuspender {
        return ClockSuspender(duration = duration, clock = clock)
    }

    @Test
    fun GIVEN_never_suspended_WHEN_isSuspended_THEN_returns_false() {
        val clock = MutableClock(start)
        assertFalse(suspender(clock).isSuspended())
    }

    @Test
    fun GIVEN_just_suspended_WHEN_isSuspended_THEN_returns_true() {
        val clock = MutableClock(start)
        val suspender = suspender(clock)
        suspender.setSuspended()
        assertTrue(suspender.isSuspended())
    }

    @Test
    fun GIVEN_suspended_WHEN_time_advances_within_duration_THEN_remains_suspended() {
        val clock = MutableClock(start)
        val suspender = suspender(clock)
        suspender.setSuspended()
        clock.instant = start.plus(29.seconds)
        assertTrue(suspender.isSuspended())
    }

    @Test
    fun GIVEN_suspended_WHEN_time_reaches_exact_boundary_THEN_not_suspended() {
        val clock = MutableClock(start)
        val suspender = suspender(clock)
        suspender.setSuspended()
        clock.instant = start.plus(30.seconds)
        assertFalse(suspender.isSuspended())
    }

    @Test
    fun GIVEN_suspended_WHEN_duration_elapsed_THEN_not_suspended() {
        val clock = MutableClock(start)
        val suspender = suspender(clock)
        suspender.setSuspended()
        clock.instant = start.plus(31.seconds)
        assertFalse(suspender.isSuspended())
    }

    @Test
    fun GIVEN_zero_duration_WHEN_suspended_THEN_not_suspended() {
        val clock = MutableClock(start)
        val suspender = suspender(clock, duration = Duration.ZERO)
        suspender.setSuspended()
        assertFalse(suspender.isSuspended())
    }

    @Test
    fun GIVEN_suspended_WHEN_setSuspended_called_again_THEN_window_restarts_from_new_moment() {
        val clock = MutableClock(start)
        val suspender = suspender(clock)
        suspender.setSuspended()

        clock.instant = start.plus(25.seconds)
        assertTrue(suspender.isSuspended())

        suspender.setSuspended()
        clock.instant = start.plus(50.seconds)
        assertTrue(suspender.isSuspended())

        clock.instant = start.plus(56.seconds)
        assertFalse(suspender.isSuspended())
    }
}
