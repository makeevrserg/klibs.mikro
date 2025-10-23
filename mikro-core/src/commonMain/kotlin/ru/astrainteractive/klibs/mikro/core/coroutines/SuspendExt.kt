package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.measureTime

suspend fun awaitForCompletion(
    delay: Duration = 50.milliseconds,
    condition: suspend () -> Boolean
) {
    while (currentCoroutineContext().isActive && !condition.invoke()) {
        val measure = measureTime { yield() }
        if (delay == Duration.ZERO) continue
        if (measure < delay) delay(delay - measure)
    }
}
