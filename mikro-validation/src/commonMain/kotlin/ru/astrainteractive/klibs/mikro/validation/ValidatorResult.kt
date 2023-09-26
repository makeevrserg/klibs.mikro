package ru.astrainteractive.klibs.mikro.validation

import kotlin.jvm.JvmInline

sealed interface ValidatorResult<out T> {
    data object Success : ValidatorResult<Nothing>

    @JvmInline
    value class Failure<T>(val violation: T) : ValidatorResult<T>

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    val violationOrNull: T?
        get() = (this as? Failure<T>)?.violation
}
