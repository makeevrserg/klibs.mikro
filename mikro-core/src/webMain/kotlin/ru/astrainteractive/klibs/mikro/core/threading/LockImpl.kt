package ru.astrainteractive.klibs.mikro.core.threading

@Suppress("FunctionNaming")
actual fun MutexLock(): Lock {
    return JsLock()
}
