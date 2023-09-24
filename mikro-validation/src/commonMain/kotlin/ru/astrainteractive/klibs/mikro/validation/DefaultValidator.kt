package ru.astrainteractive.klibs.mikro.validation

import ru.astrainteractive.klibs.mikro.validation.impl.ValidationContextImpl

class DefaultValidator<T, R>(
    private val context: ValidationContext<T, R>.() -> Unit
) : Validator<T, R> {
    override fun validate(value: T): ValidatorResult<R> {
        val validationContext = ValidationContextImpl<T, R>(value).apply(context)
        val validators = validationContext.create()
        return validators
            .map { it.validate(value) }
            .firstOrNull { it.isFailure }
            ?: ValidatorResult.Success
    }
}
