package ru.astrainteractive.klibs.mikro.core.threading

internal actual fun MutexLock(): Lock {
    return MutexLockImpl()
}
