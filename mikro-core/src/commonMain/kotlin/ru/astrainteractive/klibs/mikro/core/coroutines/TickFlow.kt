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
class TickFlow(private val duration: Duration) : Flow<Unit> by flow(
    block = {
        while (currentCoroutineContext().isActive) {
            emit(Unit)
            delay(duration)
        }
    }
)
