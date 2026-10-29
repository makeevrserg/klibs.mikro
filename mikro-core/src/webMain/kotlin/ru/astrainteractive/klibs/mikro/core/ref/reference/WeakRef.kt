@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.ref.reference

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.JsName

@JsName("WeakRef")
@OptIn(ExperimentalWasmJsInterop::class)
internal external class WeakRef {

    @Suppress("UnusedPrivateProperty")
    constructor(target: JsAny)

    fun deref(): JsAny
}
