package ru.astrainteractive.klibs.mikro.core.reuse

import ru.astrainteractive.klibs.mikro.core.ref.KWeakReference
import ru.astrainteractive.klibs.mikro.core.ref.weakReference
import ru.astrainteractive.klibs.mikro.core.threading.Lock

private class WeakRefReusable<T : Any>(private val factory: () -> T) : Reusable<T> {

    private val lock = Lock()

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

fun <T : Any> weakRefReusable(factory: () -> T): Reusable<T> = WeakRefReusable(factory)
