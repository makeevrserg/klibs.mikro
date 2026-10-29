package ru.astrainteractive.klibs.mikro.core.reuse.util

import ru.astrainteractive.klibs.mikro.core.reuse.api.Reusable
import ru.astrainteractive.klibs.mikro.core.reuse.reusable.WeakRefReusable
import ru.astrainteractive.klibs.mikro.core.threading.util.MutexLock

public fun <T : Any> weakRefReusable(factory: () -> T): Reusable<T> = WeakRefReusable(factory, MutexLock())
