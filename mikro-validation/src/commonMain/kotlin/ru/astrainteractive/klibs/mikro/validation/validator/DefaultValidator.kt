@file:Suppress("KindShape", "RolelessClassName")

package ru.astrainteractive.klibs.mikro.validation.validator

import ru.astrainteractive.klibs.mikro.validation.api.ValidationContext
import ru.astrainteractive.klibs.mikro.validation.api.Validator
import ru.astrainteractive.klibs.mikro.validation.model.ValidatorResult

public class DefaultValidator<T, R>(
    private val context: ValidationContext<T, R>.() -> Unit
) : Validator<T, R> {
    override fun validate(value: T): ValidatorResult<R> {
        val validationContext = DefaultValidationContext<T, R>(value).apply(context)
        val validators = validationContext.create()
        return validators
            .map { validator -> validator.validate(value) }
            .firstOrNull(ValidatorResult<R>::isFailure)
            ?: ValidatorResult.Success
    }
}
