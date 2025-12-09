package ru.astrainteractive.klibs.mikro.core.threading

internal actual fun Lock(): Lock {
    return NonJsMainLock()
}
