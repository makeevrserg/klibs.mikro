package ru.astrainteractive.klibs.mikro.exposed.entity

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.dao.EntityClass

@Suppress("UnnecessaryAbstractClass")
public abstract class StringEntityClass<out E : StringEntity>(
    table: IdTable<String>,
    entityType: Class<E>? = null,
    entityCtor: ((EntityID<String>) -> E)? = null
) : EntityClass<String, E>(
    table,
    entityType,
    entityCtor
)
