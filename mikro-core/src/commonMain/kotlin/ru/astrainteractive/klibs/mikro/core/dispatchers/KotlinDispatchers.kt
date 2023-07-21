package ru.astrainteractive.klibs.mikro.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

/**
 * You can pass dispatchers also as interface in your DI
 */
@Suppress("VariableNaming")
interface KotlinDispatchers {
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Default: CoroutineDispatcher
    val Unconfined: CoroutineDispatcher
}
