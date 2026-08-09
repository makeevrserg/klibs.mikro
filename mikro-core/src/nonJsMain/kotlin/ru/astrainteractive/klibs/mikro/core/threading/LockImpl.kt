package ru.astrainteractive.klibs.mikro.core.threading

@Suppress("FunctionNaming")
internal actual fun MutexLock(): Lock {
    return MutexLockImpl()
}
