package ru.astrainteractive.klibs.mikro.core.threading

actual fun MutexLock(): Lock {
    return JsLock()
}
