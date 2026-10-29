package ru.astrainteractive.klibs.mikro.extensions.arkivanov.util

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.stateflow.ValueStateFlow

public fun <T : Any> Value<T>.toStateFlow(): StateFlow<T> = ValueStateFlow(this)
