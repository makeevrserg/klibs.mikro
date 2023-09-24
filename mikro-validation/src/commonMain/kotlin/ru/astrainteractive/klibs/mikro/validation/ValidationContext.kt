package ru.astrainteractive.klibs.mikro.validation

/**
 * Context of current validation scope
 *
 * [T] - validatable value type
 *
 * [R] - result, which will be returned when validation pass failed
 */
interface ValidationContext<T, R> {
    /**
     * Validatable value
     */
    val value: T

    /**
     * Remember validation for current value
     */
    fun validate(validation: Validation<T>): Validation<T>

    /**
     * Create otherwise option whether validation not passed
     */
    infix fun Validation<T>.otherwise(value: R)

    /**
     * Create list of remembered validators
     */
    fun create(): List<Validator<T, R>>
}

