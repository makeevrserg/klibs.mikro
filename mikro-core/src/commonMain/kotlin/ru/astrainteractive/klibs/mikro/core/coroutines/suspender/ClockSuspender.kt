@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.coroutines.suspender

import ru.astrainteractive.klibs.mikro.core.coroutines.api.Suspender
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
public class ClockSuspender(
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
