package ru.astrainteractive.klibs.mikro.core.ref.util

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import ru.astrainteractive.klibs.mikro.core.ref.reference.JsKWeakReference
import ru.astrainteractive.klibs.mikro.core.ref.reference.WeakRef
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toJsReference

@OptIn(ExperimentalWasmJsInterop::class)
public actual fun <T : Any> weakReference(value: T): KWeakReference<T> {
    return JsKWeakReference(WeakRef(value.toJsReference()))
}
