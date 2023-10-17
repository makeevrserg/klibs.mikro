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
    fun validate(otherwise: R, validation: Validation<T>): Validation<T>

    /**
     * Remember validation for current value
     */
    fun validate(otherwise: (T) -> R, validation: Validation<T>): Validation<T>

    /**
     * Create list of remembered validators
     */
    fun create(): List<Validator<T, R>>
}
