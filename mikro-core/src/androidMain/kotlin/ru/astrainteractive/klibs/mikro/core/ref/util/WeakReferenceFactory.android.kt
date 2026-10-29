package ru.astrainteractive.klibs.mikro.core.ref.util

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import ru.astrainteractive.klibs.mikro.core.ref.reference.JavaKWeakReference
import java.lang.ref.WeakReference

public actual fun <T : Any> weakReference(value: T): KWeakReference<T> {
    return JavaKWeakReference(WeakReference(value))
}
