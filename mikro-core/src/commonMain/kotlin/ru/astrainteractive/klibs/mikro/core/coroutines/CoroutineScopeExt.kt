package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.mikro.core.logging.Logger
import ru.astrainteractive.klibs.mikro.core.logging.StubLogger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun CoroutineScope.launch(
    mutex: Mutex,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) = launch(
    context = context,
    start = start,
    block = {
        mutex.withLock {
            block.invoke(this)
        }
    }
)

fun CoroutineScope.launchOnCompletion(
    logger: Logger = StubLogger,
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
                    logger.error(t) { "#launchOnCompletion 7 could not execute block" }
                }
            }
        }
    }
}
