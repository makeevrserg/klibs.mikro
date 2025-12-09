package ru.astrainteractive.klibs.mikro.core.threading

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.WeakReference

@OptIn(ExperimentalNativeApi::class)
private class NativeKWeakReference<T : Any>(private val reference: WeakReference<T>) : KWeakReference<T> {
    override val value: T?
        get() = reference.get()
}

@OptIn(ExperimentalNativeApi::class)
actual fun <T : Any> weakReference(value: T): KWeakReference<T> {
    return NativeKWeakReference(WeakReference(value))
}
