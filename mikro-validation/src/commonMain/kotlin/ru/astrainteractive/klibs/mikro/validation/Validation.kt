package ru.astrainteractive.klibs.mikro.validation

fun interface Validation<T> {
    fun validate(value: T): Boolean
}
