package ru.astrainteractive.klibs.mikro.validation

/**
 * This validator can help to create validators on UI TextFields
 */
fun interface Validator<T, R> {

    /**
     * This function will run validation on [value]
     * @return true if validation is passed
     */
    fun validate(value: T): ValidatorResult<R>
}
