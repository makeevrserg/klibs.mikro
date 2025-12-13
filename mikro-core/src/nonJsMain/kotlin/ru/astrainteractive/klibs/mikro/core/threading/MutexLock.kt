package ru.astrainteractive.klibs.mikro.core.threading

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class MutexLock : Lock {
    private val mutex = Mutex()
    override fun <T> synchronized(block: () -> T): T {
        return runBlocking {
            mutex.withLock(this) {
                block.invoke()
            }
        }
    }
}
