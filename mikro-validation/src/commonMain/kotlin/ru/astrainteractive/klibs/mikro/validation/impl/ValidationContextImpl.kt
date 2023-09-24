package ru.astrainteractive.klibs.mikro.validation.impl

import ru.astrainteractive.klibs.mikro.validation.Validation
import ru.astrainteractive.klibs.mikro.validation.ValidationContext
import ru.astrainteractive.klibs.mikro.validation.Validator
import ru.astrainteractive.klibs.mikro.validation.ValidatorResult

internal class ValidationContextImpl<T, R>(override val value: T) : ValidationContext<T, R> {
    private data class ValidationData<T, R>(
        val validation: Validation<T>,
        val violdation: R?,
        val index: Int
    )
    private var i = 0
    private val validations = mutableListOf<ValidationData<T, R>>()

    override fun validate(validation: Validation<T>): Validation<T> {
        val data = ValidationData<T, R>(
            validation = validation,
            violdation = null,
            index = i++
        )
        validations.add(data)
        return validation
    }

    override infix fun Validation<T>.otherwise(value: R) {
        validations[i - 1] = validations.elementAt(i - 1).copy(violdation = value)
    }

    override fun create(): List<Validator<T, R>> {
        return validations
            .sortedBy { it.index }
            .mapNotNull { (validation, violation) ->
                if (violation == null) return@mapNotNull null
                validation to violation
            }.map { (validation, violation) ->
                Validator { value ->
                    val isValid = validation.validate(value)
                    if (isValid) ValidatorResult.Success
                    else ValidatorResult.Failure(violation)
                }
            }

    }
}