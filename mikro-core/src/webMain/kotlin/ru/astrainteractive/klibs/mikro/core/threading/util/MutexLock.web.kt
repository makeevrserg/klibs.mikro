package ru.astrainteractive.klibs.mikro.core.threading.util

import ru.astrainteractive.klibs.mikro.core.threading.api.Lock
import ru.astrainteractive.klibs.mikro.core.threading.mutex.JsLock

@Suppress("FunctionNaming")
internal actual fun MutexLock(): Lock {
    return JsLock()
}
