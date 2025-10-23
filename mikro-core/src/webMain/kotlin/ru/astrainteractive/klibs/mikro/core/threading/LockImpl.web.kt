package ru.astrainteractive.klibs.mikro.core.threading

actual fun Lock(): Lock {
    return JsLock()
}
