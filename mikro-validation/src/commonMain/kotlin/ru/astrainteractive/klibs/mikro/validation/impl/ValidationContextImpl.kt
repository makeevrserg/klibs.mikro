package ru.astrainteractive.klibs.mikro.validation.impl

import ru.astrainteractive.klibs.mikro.validation.Validation
import ru.astrainteractive.klibs.mikro.validation.ValidationContext
import ru.astrainteractive.klibs.mikro.validation.Validator
import ru.astrainteractive.klibs.mikro.validation.ValidatorResult

internal class ValidationContextImpl<T, R>(override val value: T) : ValidationContext<T, R> {
    private data class ValidationData<T, R>(
        val validation: Validation<T>,
        val violdation: R,
        val index: Int
    )

    private var i = 0
    private val validations = mutableListOf<ValidationData<T, R>>()

    override fun validate(otherwise: (T) -> R, validation: Validation<T>): Validation<T> {
        val otherwiseResult = otherwise.invoke(value)
        return validate(otherwiseResult, validation)
    }

    override fun validate(otherwise: R, validation: Validation<T>): Validation<T> {
        val data = ValidationData<T, R>(
            validation = validation,
            violdation = otherwise,
            index = i++
        )
        validations.add(data)
        return validation
    }

    override fun create(): List<Validator<T, R>> {
        return validations
            .sortedBy { it.index }
            .map { validationData ->
                Validator { value ->
                    val isValid = validationData.validation.validate(value)
                    if (isValid) {
                        ValidatorResult.Success
                    } else {
                        ValidatorResult.Failure(validationData.violdation)
                    }
                }
            }
    }
}
