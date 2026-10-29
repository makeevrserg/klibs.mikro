package ru.astrainteractive.klibs.locale.util

import ru.astrainteractive.klibs.locale.model.SharedLocale
import java.util.Locale

private fun String.capitalized(): String {
    return replaceFirstChar { character ->
        if (character.isLowerCase()) {
            character.titlecase(Locale.getDefault())
        } else {
            character.toString()
        }
    }
}

internal fun Locale.toSharedLocale(): SharedLocale {
    return SharedLocale(
        code = SharedLocale.Code(language),
        name = SharedLocale.Name(displayLanguage.capitalized())
    )
}
