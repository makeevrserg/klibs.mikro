package ru.astrainteractive.klibs.mikro.core.ref

expect fun <T : Any> weakReference(value: T): KWeakReference<T>
