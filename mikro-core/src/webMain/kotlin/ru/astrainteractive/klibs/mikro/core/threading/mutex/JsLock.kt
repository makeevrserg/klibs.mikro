@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.threading.mutex

import ru.astrainteractive.klibs.mikro.core.threading.api.Lock

public class JsLock : Lock {
    override fun <T> synchronized(block: () -> T): T {
        return block.invoke()
    }
}
