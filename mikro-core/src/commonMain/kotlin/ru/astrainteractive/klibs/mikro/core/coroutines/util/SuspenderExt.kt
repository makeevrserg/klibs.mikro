package ru.astrainteractive.klibs.mikro.core.coroutines.util

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import ru.astrainteractive.klibs.mikro.core.coroutines.api.Suspender
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.measureTime

public suspend fun awaitForCompletion(
    delayDuration: Duration = 50.milliseconds,
    condition: suspend () -> Boolean
) {
    while (currentCoroutineContext().isActive && !condition.invoke()) {
        val measure = measureTime { yield() }
        if (delayDuration == Duration.ZERO) continue
        if (measure < delayDuration) delay(delayDuration - measure)
    }
}

public suspend fun Suspender.awaitNotSuspended(delay: Duration = 50.milliseconds) {
    awaitForCompletion(delay) { !isSuspended() }
}
