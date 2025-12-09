package ru.astrainteractive.klibs.mikro.core.threading

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.JsName
import kotlin.js.JsReference
import kotlin.js.get
import kotlin.js.toJsReference
import kotlin.js.unsafeCast

@JsName("WeakRef")
@OptIn(ExperimentalWasmJsInterop::class)
private external class WeakRef {

    @Suppress("UnusedPrivateProperty")
    constructor(target: JsAny)

    fun deref(): JsAny
}

@OptIn(ExperimentalWasmJsInterop::class)
private class JsKWeakReference<T : Any>(private val reference: WeakRef) : KWeakReference<T> {
    override val value: T?
        get() = reference.deref()?.unsafeCast<JsReference<T>>()?.get()
}

@OptIn(ExperimentalWasmJsInterop::class)
actual fun <T : Any> weakReference(value: T): KWeakReference<T> {
    return JsKWeakReference(WeakRef(value.toJsReference()))
}
