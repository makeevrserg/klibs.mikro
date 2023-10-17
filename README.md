[![version](https://img.shields.io/maven-central/v/ru.astrainteractive.klibs/mikro-core?style=flat-square)](https://github.com/makeevrserg/kstorage)
[![kotlin_version](https://img.shields.io/badge/kotlin-1.9.0-blueviolet?style=flat-square)](https://github.com/makeevrserg/kstorage)

## MiKro

MiKro is super lightweight Kotlin Multiplatform library with simple utilities

## Installation

Gradle

```kotlin
implementation("ru.astrainteractive.klibs:mikro-core:<version>")
implementation("ru.astrainteractive.klibs:mikro-platform:<version>")
```

Version catalogs

```toml
[versions]
klibs-mikro = "<latest-version>"

[libraries]
klibs-mikro-core = { module = "ru.astrainteractive.klibs:mikro-core", version.ref = "klibs-mikro" }
klibs-mikro-platform = { module = "ru.astrainteractive.klibs:mikro-platform", version.ref = "klibs-mikro" }
```

### Platform
```kotlin
object SomeDI {
    lateinit var platform: PlatformConfiguration
}

// android application
fun onCreate() {
    SomeDI.platform = DefaultAndroidPlatformConfiguration(applicationContext)
}
// on jvm there's DefaultJVMPlatformConfiguration
// for js there's DefaultJSPlatformConfiguration
// for native there's DefaultNativePlatformConfiguration
```

### Dispatchers
Use it in repositories
```kotlin
class MyRepository(
    private val customDispatchers: KotlinDispatchers = DefaultKotlinDispatchers
) {
    suspend fun getSomeValue() = withContext(customDispatchers.IO) {
        // some logic
    }
}
Or create custom
class CustomKDispatchers : KotlinDispatchers {
    override val Main: CoroutineDispatcher
        get() = Dispatchers.Default
    override val IO: CoroutineDispatcher
        get() = IODispatcher.Default
    override val Default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val Unconfined: CoroutineDispatcher
        get() = Dispatchers.Default
}
```

### Mapper
```kotlin
class StringMapper : Mapper<String, Int> {
    override fun toDTO(it: String): Int {
        return it.toInt()
    }

    override fun fromDTO(it: Int): String {
        return it.toString()
    }
}
```

### UseCase

```kotlin
// Use simple UseCase
class IntUseCase : UseCase.Simple<Int> {
    override suspend fun invoke(): Int {
        return 10
    }
}

// Or Parametrized
class MultiplyUseCase : UseCase.Parametrized<MultiplyUseCase.Param, Int> {
    class Param(val value: Int, val multiplyBy: Int)

    override suspend fun invoke(input: Param): Int {
        return input.value * input.multiplyBy
    }
}
```

### EnumExt
```kotlin
enum class NumEnum {
    ONE, TWO, THREE
}

fun sample() {
    val oneEnum = NumEnum.ONE
    val twoEnum = oneEnum.next()
    val oneEnumAgain = oneEnum.next().next()
}
```
### MapStateFlowExt
```kotlin
fun sample(): StateFlow<String> {
    val stringStateFlow = MutableStateFlow("String")
    val intStateFlow = MutableStateFlow(10)
    return combineStates(
        flow1 = stringStateFlow,
        flow2 = intStateFlow,
        transform = { s, i ->
            "string: $s; int: $i"
        }
    )
}
```

## Locale
Platform: JVM, iOS
```kotlin
// Create Locale Provider
val localeProvider = LocaleProvider()
// will get available locales with code, name
localeProvider.availableLocales
// will give current system language
localeProvider.systemDefaultLanguage
// Will return ru_RU locale info
localeProvider.fromCode(SharedLocale.Code("ru_RU"))
localeProvider.fromCode(SharedLocale.Code("ru"))
```