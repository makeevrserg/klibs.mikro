package ru.astrainteractive.klibs.mikro.core.util

inline fun <reified T : kotlin.Enum<T>> T.addIndex(offset: Int, values: Array<T>): T {
    var res = ordinal + offset
    if (res <= -1) res = values.size - 1
    val index = res % values.size
    return values[index]
}

inline fun <reified T : kotlin.Enum<T>> T.next(values: Array<T>): T {
    return addIndex(1, values)
}

inline fun <reified T : kotlin.Enum<T>> T.prev(values: Array<T>): T {
    return addIndex(-1, values)
}
