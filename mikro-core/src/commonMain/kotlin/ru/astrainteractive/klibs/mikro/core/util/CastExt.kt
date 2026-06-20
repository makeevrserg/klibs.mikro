package ru.astrainteractive.klibs.mikro.core.util

inline fun <reified T> Any.tryCast() = this as? T

inline fun <reified T : Any> T?.orElse(block: () -> T) = this ?: block.invoke()