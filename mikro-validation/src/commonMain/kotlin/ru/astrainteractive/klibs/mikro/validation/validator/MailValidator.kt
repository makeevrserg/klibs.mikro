@file:Suppress("KindShape", "RolelessClassName")

package ru.astrainteractive.klibs.mikro.validation.validator

import ru.astrainteractive.klibs.mikro.validation.api.Validator

public class MailValidator : Validator<String, String> by DefaultValidator(
    context = {
        validate("Not contains @ symbol") { value -> value.contains("@") }
    }
)
