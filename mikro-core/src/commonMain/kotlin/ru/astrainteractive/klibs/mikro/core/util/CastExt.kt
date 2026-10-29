package ru.astrainteractive.klibs.mikro.core.util

public inline fun <reified T> Any.tryCast(): T? = this as? T

public inline fun <reified T : Any> T?.orElse(block: () -> T): T = this ?: block.invoke()
