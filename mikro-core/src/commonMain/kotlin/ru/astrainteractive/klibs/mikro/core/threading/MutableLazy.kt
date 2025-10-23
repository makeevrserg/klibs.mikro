package ru.astrainteractive.klibs.mikro.core.threading

import kotlin.reflect.KProperty

/**
 * A thread-safe, mutable variant of [Lazy] for Kotlin Multiplatform.
 *
 * The value is initialized on first access using [initializer] and can be reassigned later.
 * Safe for concurrent access across platforms (JVM, JS, Native).
 */
internal class MutableLazy<T>(initializer: () -> T) : Lazy<T> {
    private var initializer: (() -> T)? = initializer

    object UNINITIALIZED

    private val lock = Lock()

    private var _value: Any? = UNINITIALIZED

    override var value: T
        get() {
            val _v1 = _value
            if (_v1 !== UNINITIALIZED) {
                @Suppress("UNCHECKED_CAST")
                return _v1 as T
            }
            return lock.synchronized {
                val _v2 = _value
                if (_v2 !== UNINITIALIZED) {
                    @Suppress("UNCHECKED_CAST")
                    _v2 as T
                } else {
                    val localInitializer = initializer
                        ?: error("Initializer was null during initialization")
                    val typedValue = localInitializer.invoke()
                    _value = typedValue
                    initializer = null
                    typedValue
                }
            }
        }
        set(newValue) {
            _value = newValue
        }

    override fun isInitialized(): Boolean {
        return _value != UNINITIALIZED
    }

    operator fun getValue(thisRef: Nothing?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Nothing?, property: KProperty<*>, newValue: T) {
        value = newValue
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        value = newValue
    }
}

/**
 * Creates a thread-safe [MutableLazy] instance with the given [initializer].
 */
internal fun <T> mutableLazy(initializer: () -> T): MutableLazy<T> = MutableLazy(initializer)
