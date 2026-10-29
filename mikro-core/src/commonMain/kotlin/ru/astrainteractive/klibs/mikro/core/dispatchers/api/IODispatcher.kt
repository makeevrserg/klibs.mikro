package ru.astrainteractive.klibs.mikro.core.dispatchers.api

import kotlinx.coroutines.CoroutineDispatcher

internal expect object IODispatcher {
    val IO: CoroutineDispatcher
}
