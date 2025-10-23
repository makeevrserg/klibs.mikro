package ru.astrainteractive.klibs.mikro.core.threading

interface Lock {
    fun <T> synchronized(block: () -> T): T
}
