@file:Suppress("RolelessClassName")

package ru.astrainteractive.klibs.mikro.validation.api

import ru.astrainteractive.klibs.mikro.validation.model.ValidatorResult

/**
 * This validator can help to create validators on UI TextFields
 */
public fun interface Validator<T, R> {

    /**
     * This function will run validation on [value]
     * @return true if validation is passed
     */
    public fun validate(value: T): ValidatorResult<R>
}
