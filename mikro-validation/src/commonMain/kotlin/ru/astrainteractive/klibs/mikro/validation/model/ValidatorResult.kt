package ru.astrainteractive.klibs.mikro.validation.model

import kotlin.jvm.JvmInline

public sealed interface ValidatorResult<out T> {
    public data object Success : ValidatorResult<Nothing>

    @JvmInline
    public value class Failure<T>(public val violation: T) : ValidatorResult<T>

    public val isSuccess: Boolean
        get() = this is Success

    public val isFailure: Boolean
        get() = this is Failure

    public val violationOrNull: T?
        get() = (this as? Failure<T>)?.violation
}
