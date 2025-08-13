package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * A utility class that manages a simple suspension mechanism based on a duration.
 *
 * Once [setSuspended] is called, the suspender enters a suspended state for the
 * specified [duration]. During this period, [isSuspended] will return `true`.
 * After the duration has elapsed, [isSuspended] will return `false`.
 *
 * Example usage:
 * ```
 * val suspender = Suspender(Duration.seconds(30))
 * suspender.setSuspended()
 * if (suspender.isSuspended()) {
 *     println("Still suspended.")
 * }
 * ```
 *
 * @property duration The duration for which the object remains suspended after being triggered.
 */
interface Suspender {
    /**
     * Checks whether the current time is still within the suspension period.
     *
     * @return `true` if the suspender is still within the active suspension window, `false` otherwise.
     */
    fun isSuspended(): Boolean

    /**
     * Marks the current moment as the start of the suspension period.
     */
    fun setSuspended()
}

@OptIn(ExperimentalTime::class)
class KotlinTimeSuspender(
    private val duration: Duration,
    private val clock: Clock = Clock.System
) : Suspender {
    private var lastSuspendedAt: Instant = Instant.DISTANT_PAST

    override fun isSuspended(): Boolean {
        val now = clock.now()
        return lastSuspendedAt.plus(duration) > now
    }

    override fun setSuspended() {
        lastSuspendedAt = clock.now()
    }
}

suspend fun Suspender.awaitNotSuspended(delay: Duration = 50.milliseconds) {
    awaitForCompletion(delay) { !isSuspended() }
}
