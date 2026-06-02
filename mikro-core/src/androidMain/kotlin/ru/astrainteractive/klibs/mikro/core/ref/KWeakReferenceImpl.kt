package ru.astrainteractive.klibs.mikro.core.ref

import java.lang.ref.Reference
import java.lang.ref.WeakReference

private class JavaKWeakReference<T>(private val reference: Reference<T>) : KWeakReference<T> {
    override val value: T?
        get() = reference.get()
}

actual fun <T : Any> weakReference(value: T): KWeakReference<T> {
    return JavaKWeakReference(WeakReference(value))
}
