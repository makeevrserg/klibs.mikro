package ru.astrainteractive.klibs.locale

import kotlin.test.Test
import kotlin.test.assertNotNull

class LocaleProviderTest {
    @Test
    fun testLocaleNotNull() {
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
