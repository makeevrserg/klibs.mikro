package ru.astrainteractive.klibs.mikro.core.threading

expect fun <T : Any> weakReference(value: T): KWeakReference<T>
