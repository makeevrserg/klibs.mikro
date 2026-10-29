package ru.astrainteractive.klibs.mikro.core.threading.api

public interface Lock {
    public fun <T> synchronized(block: () -> T): T
}
