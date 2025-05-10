package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.yield

suspend fun awaitForCompletion(isCompleted: suspend () -> Boolean) {
    while (!isCompleted.invoke()) {
        yield()
    }
}
