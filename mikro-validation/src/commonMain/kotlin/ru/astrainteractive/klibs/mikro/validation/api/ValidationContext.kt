package ru.astrainteractive.klibs.mikro.validation.api

/**
 * Context of current validation scope
 *
 * [T] - validatable value type
 *
 * [R] - result, which will be returned when validation pass failed
 */
public interface ValidationContext<T, R> {
    /**
     * Validatable value
     */
    public val value: T

    /**
     * Remember validation for current value
     */
    public fun validate(otherwise: R, validation: Validation<T>): Validation<T>

    /**
     * Remember validation for current value
     */
    public fun validate(otherwise: (T) -> R, validation: Validation<T>): Validation<T>

    /**
     * Create list of remembered validators
     */
    public fun create(): List<Validator<T, R>>
}
