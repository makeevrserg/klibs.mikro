@file:Suppress("FunctionNaming")

package ru.astrainteractive.klibs.locale.api

import ru.astrainteractive.klibs.locale.model.SharedLocale
import ru.astrainteractive.klibs.locale.util.LocaleProvider
import kotlin.test.Test
import kotlin.test.assertNotNull

class LocaleProviderTest {
    @Test
    fun GIVEN_popular_locale_codes_WHEN_from_code_THEN_returns_locale() {
        val localeProvider = LocaleProvider()
        val popularCodes = listOf(
            "ru_RU",
            "en_US",
            "ru",
            "en",
            "ja",
            "fr",
            "de",
            "it",
            "ko"
        )
        popularCodes.map(SharedLocale::Code).forEach { code ->
            val sharedLocale = localeProvider.fromCode(code)
            assertNotNull(sharedLocale)
        }
    }
}
