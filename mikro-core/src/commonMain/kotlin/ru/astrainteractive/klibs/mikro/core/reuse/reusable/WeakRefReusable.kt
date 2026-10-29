@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.reuse.reusable

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import ru.astrainteractive.klibs.mikro.core.ref.util.weakReference
import ru.astrainteractive.klibs.mikro.core.reuse.api.Reusable
import ru.astrainteractive.klibs.mikro.core.threading.api.Lock

internal class WeakRefReusable<T : Any>(
    private val factory: () -> T,
    private val lock: Lock
) : Reusable<T> {

    var ref: KWeakReference<T>? = null

    override val value: T
        get() {
            ref?.value?.let { instance -> return instance }

            return lock.synchronized {
                ref?.value?.let { instance -> return@synchronized instance }

                val newValue = factory.invoke()
                ref = weakReference(newValue)
                return@synchronized newValue
            }
        }

    override val orNull: T?
        get() = ref?.value

    override val isReferenced: Boolean
        get() = ref?.value != null
}
