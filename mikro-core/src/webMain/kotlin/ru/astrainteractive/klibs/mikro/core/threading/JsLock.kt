package ru.astrainteractive.klibs.mikro.core.threading

class JsLock : Lock {
    override fun <T> synchronized(block: () -> T): T {
        return block.invoke()
    }
}
