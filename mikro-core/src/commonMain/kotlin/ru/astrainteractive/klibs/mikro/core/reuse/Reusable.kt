package ru.astrainteractive.klibs.mikro.core.reuse

interface Reusable<T : Any> {
    val value: T

    val orNull: T?

    val isReferenced: Boolean
}
