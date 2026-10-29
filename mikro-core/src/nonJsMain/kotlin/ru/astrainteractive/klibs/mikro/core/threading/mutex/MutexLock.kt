@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.threading.mutex

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.astrainteractive.klibs.mikro.core.threading.api.Lock

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
