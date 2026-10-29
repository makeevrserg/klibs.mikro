package ru.astrainteractive.klibs.mikro.validation.api

public fun interface Validation<T> {
    public fun validate(value: T): Boolean
}
