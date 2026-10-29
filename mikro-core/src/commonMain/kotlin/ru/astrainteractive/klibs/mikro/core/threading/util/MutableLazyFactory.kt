package ru.astrainteractive.klibs.mikro.core.threading.util

import ru.astrainteractive.klibs.mikro.core.threading.lazy.MutableLazy

internal fun <T> mutableLazy(initializer: () -> T): MutableLazy<T> = MutableLazy(initializer, MutexLock())
