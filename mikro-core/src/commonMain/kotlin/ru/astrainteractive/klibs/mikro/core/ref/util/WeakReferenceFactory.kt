package ru.astrainteractive.klibs.mikro.core.ref.util

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference

public expect fun <T : Any> weakReference(value: T): KWeakReference<T>
