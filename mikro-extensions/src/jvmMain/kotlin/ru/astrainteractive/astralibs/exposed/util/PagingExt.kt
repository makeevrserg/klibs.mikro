package ru.astrainteractive.astralibs.exposed.util

import org.jetbrains.exposed.sql.AbstractQuery

fun <T : AbstractQuery<T>> AbstractQuery<T>.paging(maxCount: Int, page: Int): T {
    return limit(maxCount).offset(maxCount * page * 1L)
}
