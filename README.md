[![version](https://img.shields.io/maven-central/v/ru.astrainteractive.klibs/mikro-core?style=flat-square)](https://github.com/makeevrserg/kstorage)
[![kotlin_version](https://img.shields.io/badge/kotlin-1.9.0-blueviolet?style=flat-square)](https://github.com/makeevrserg/kstorage)

## 🌟 Mikro Kotlin Extensions Suite

MiKro is super lightweight Kotlin Multiplatform library with simple utilities

---

## 🔍 What Is This Project?

This **Mikro Kotlin Extensions Suite** is a modular extensions code utilities for Kotlin projects

- ⚙️ `mikro-core` - kotlin first-party libraries extensions
- 📱 `mikro-extensions` - kotlin third-party libraries extensions
- ⛏️ `other` - uwu sowwy, no descwiption, because you pwobably won't use it

---

## 🚀 How to Use

### 1️⃣ Define the required plugins

In your `libs.version.toml`

```toml
[versions]
klibs-mikro = "<latest-version>"

[libraries]
klibs-mikro-core = { module = "ru.astrainteractive.klibs:mikro-core", version.ref = "klibs-mikro" }
klibs-mikro-extensions = { module = "ru.astrainteractive.klibs:mikro-extensions", version.ref = "klibs-mikro" }
klibs-mikro-locale = { module = "ru.astrainteractive.klibs:mikro-locale", version.ref = "klibs-mikro" }
klibs-mikro-platform = { module = "ru.astrainteractive.klibs:mikro-platform", version.ref = "klibs-mikro" }
klibs-mikro-validation = { module = "ru.astrainteractive.klibs:mikro-validation", version.ref = "klibs-mikro" }
```

### 2️⃣ Set up your Gradle plugins

```kotlin
implementation("ru.astrainteractive.klibs:mikro-core:<version>")
implementation("ru.astrainteractive.klibs:mikro-extensions:<version>")
implementation("ru.astrainteractive.klibs:mikro-locale:<version>")
implementation("ru.astrainteractive.klibs:mikro-platform:<version>")
implementation("ru.astrainteractive.klibs:mikro-validation:<version>")
```

---

## 💜 Support Us

If our projects help you, consider supporting their development.

<table>
<tr>
<td align="center" width="130">
<img src="https://cdn.simpleicons.org/bitcoin/F7931A" width="25" alt="BTC"/><br/>
<sub><b>Bitcoin</b></sub>
</td>
<td>

```text
bc1q9a8dr55jgfae0mhevw3vvczegjv0khfp0ngrnv
```

</td>
</tr>
<tr>
<td align="center" width="130">
<img src="https://cdn.simpleicons.org/ethereum/627EEA" width="25" alt="ETH"/><br/>
<sub><b>Ethereum</b></sub>
</td>
<td>

```text
0x0BaAeEA44Ce08c8DC139224ff57563695B30d423
```

</td>
</tr>
<tr>
<td align="center" width="130">
<img src="https://cdn.simpleicons.org/boosty/F15F2C" width="25" alt="Boosty"/><br/>
<sub><b>Boosty</b></sub>
</td>
<td align="center">
<a href="https://boosty.to/empireprojekt/donate">
<img width="70%" src="https://img.shields.io/badge/Donate-Boosty-F15F2C?style=for-the-badge&logo=boosty&logoColor=white" alt="Donate on Boosty"/>
</a>
</td>
</tr>
</table>

---