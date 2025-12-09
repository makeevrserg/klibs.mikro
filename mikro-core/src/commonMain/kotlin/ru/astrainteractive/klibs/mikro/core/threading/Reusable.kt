package ru.astrainteractive.klibs.mikro.core.threading

interface Reusable<T : Any> {
    val value: T

    val orNull: T?

    val isReferenced: Boolean
}

private class ReusableImpl<T : Any>(private val factory: () -> T) : Reusable<T> {

    private val lock = Lock()

    var ref: KWeakReference<T>? = null

    override val value: T
        get() {
            ref?.value?.let { instance -> return instance }

            return lock.synchronized {
                ref?.value?.let { return@synchronized it }

                val newValue = factory()
                ref = weakReference(newValue)
                return@synchronized newValue
            }
        }

    override val orNull: T?
        get() = ref?.value

    override val isReferenced: Boolean
        get() = ref?.value != null
}

fun <T : Any> weakRefReusable(factory: () -> T): Reusable<T> = ReusableImpl(factory)
