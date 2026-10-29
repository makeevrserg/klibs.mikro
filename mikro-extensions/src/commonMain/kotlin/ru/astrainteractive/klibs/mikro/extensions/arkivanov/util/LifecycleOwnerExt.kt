package ru.astrainteractive.klibs.mikro.extensions.arkivanov.util

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * Creates [CoroutineScope] which is bind to current [LifecycleOwner]
 *
 * @author @arkivanov
 * @see [Source](https://github.com/arkivanov/nowinandroid/blob/decompose/core/decompose-utils/src/main/kotlin/com/google/samples/apps/nowinandroid/core/decompose/utils/CoroutineScope.kt)
 */
@Suppress("MaxLineLength")
public fun LifecycleOwner.coroutineScope(
    context: CoroutineContext = Dispatchers.Main.immediate,
): CoroutineScope {
    return CoroutineScope(context = context).also { scope ->
        lifecycle.doOnDestroy(scope::cancel)
    }
}
