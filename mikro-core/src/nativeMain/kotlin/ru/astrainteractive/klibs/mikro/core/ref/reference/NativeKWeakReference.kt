@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.ref.reference

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.ref.WeakReference

@OptIn(ExperimentalNativeApi::class)
internal class NativeKWeakReference<T : Any>(private val reference: WeakReference<T>) : KWeakReference<T> {
    override val value: T?
        get() = reference.get()
}
