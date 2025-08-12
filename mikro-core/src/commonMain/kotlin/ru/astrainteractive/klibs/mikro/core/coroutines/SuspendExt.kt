package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.yield
import kotlin.system.measureTimeMillis
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

suspend fun awaitForCompletion(
    delay: Duration = 50.milliseconds,
    condition: suspend () -> Boolean
) {
    while (currentCoroutineContext().isActive && !condition.invoke()) {
        val measure = measureTimeMillis { yield() }.milliseconds
        if (measure < delay) delay(delay - measure)
    }
}
