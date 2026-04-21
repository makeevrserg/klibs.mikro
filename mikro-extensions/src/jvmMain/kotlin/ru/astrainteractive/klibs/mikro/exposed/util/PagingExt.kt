package ru.astrainteractive.klibs.mikro.exposed.util

import org.jetbrains.exposed.v1.jdbc.Query
import org.jetbrains.exposed.v1.jdbc.SizedIterable

fun <T> SizedIterable<T>.paging(maxCount: Int, page: Int): SizedIterable<T> {
    return limit(maxCount).offset(maxCount * page * 1L)
}

fun Query.paging(maxCount: Int, page: Int): Query {
    return limit(maxCount).offset(maxCount * page * 1L)
}
