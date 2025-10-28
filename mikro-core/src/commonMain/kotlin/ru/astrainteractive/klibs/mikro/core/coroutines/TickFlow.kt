package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlin.time.Duration

/**
 * Tick every [duration]
 */
class TickFlow(
    private val duration: Duration,
    private val initialDelay: Duration = Duration.ZERO
) : Flow<Unit> by flow(
    block = {
        if (initialDelay != Duration.ZERO) delay(initialDelay)
        while (currentCoroutineContext().isActive) {
            emit(Unit)
            delay(duration)
        }
    }
)
