package ru.astrainteractive.klibs.mikro.extensions.arkivanov

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * @see <a href="https://github.com/arkivanov/nowinandroid/blob/decompose/core/decompose-utils/src/main/kotlin/com/google/samples/apps/nowinandroid/core/decompose/utils/CoroutineScope.kt">Source</href>
 *
 * @author @arkivanov
 */
@Suppress("MaxLineLength")
object CoroutineScopeExt {

    /**
     * Creates [CoroutineScope] which is bind to current [LifecycleOwner]
     */
    fun LifecycleOwner.coroutineScope(
        context: CoroutineContext = Dispatchers.Main.immediate,
    ): CoroutineScope = CoroutineScope(context = context).also { scope ->
        lifecycle.doOnDestroy(scope::cancel)
    }
}
