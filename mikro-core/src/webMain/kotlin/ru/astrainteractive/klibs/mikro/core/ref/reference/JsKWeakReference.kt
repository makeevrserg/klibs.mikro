@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.ref.reference

import ru.astrainteractive.klibs.mikro.core.ref.api.KWeakReference
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsReference
import kotlin.js.get
import kotlin.js.unsafeCast

@OptIn(ExperimentalWasmJsInterop::class)
internal class JsKWeakReference<T : Any>(private val reference: WeakRef) : KWeakReference<T> {
    override val value: T?
        get() = reference.deref()?.unsafeCast<JsReference<T>>()?.get()
}
