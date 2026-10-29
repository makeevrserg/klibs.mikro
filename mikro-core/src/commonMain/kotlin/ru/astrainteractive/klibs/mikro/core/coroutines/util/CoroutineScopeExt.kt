package ru.astrainteractive.klibs.mikro.core.coroutines.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.logging.api.Logger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun CoroutineScope.launch(
    mutex: Mutex,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return launch(
        context = context,
        start = start,
        block = {
            mutex.withLock {
                block.invoke(this)
            }
        }
    )
}

public fun CoroutineScope.launchOnCompletion(
    logger: Logger,
    block: suspend () -> Unit
) {
    launch(start = CoroutineStart.UNDISPATCHED) {
        try {
            awaitCancellation()
        } finally {
            withContext(NonCancellable) {
                try {
                    block.invoke()
                } catch (t: Throwable) {
                    logger.error(t) { "#launchOnCompletion could not execute block" }
                }
            }
        }
    }
}
