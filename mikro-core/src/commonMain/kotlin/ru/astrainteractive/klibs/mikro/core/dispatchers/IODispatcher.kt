package ru.astrainteractive.klibs.mikro.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

internal expect object IODispatcher {
    val IO: CoroutineDispatcher
}
