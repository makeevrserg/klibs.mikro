package ru.astrainteractive.klibs.mikro.core.ref.util

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import ru.astrainteractive.klibs.mikro.core.ref.reference.NativeKWeakReference
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.WeakReference

@OptIn(ExperimentalNativeApi::class)
public actual fun <T : Any> weakReference(value: T): KWeakReference<T> {
    return NativeKWeakReference(WeakReference(value))
}
