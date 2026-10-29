@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.ref.reference

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import java.lang.ref.Reference

internal class JavaKWeakReference<T>(private val reference: Reference<T>) : KWeakReference<T> {
    override val value: T?
        get() = reference.get()
}
