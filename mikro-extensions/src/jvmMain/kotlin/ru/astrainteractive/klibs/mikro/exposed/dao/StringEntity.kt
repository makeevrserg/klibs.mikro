package ru.astrainteractive.klibs.mikro.exposed.dao

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IdTable
import org.jetbrains.exposed.v1.dao.Entity
import org.jetbrains.exposed.v1.dao.EntityClass

@Suppress("UnnecessaryAbstractClass")
abstract class StringEntity(id: EntityID<String>) : Entity<String>(id)

@Suppress("UnnecessaryAbstractClass")
abstract class StringEntityClass<out E : StringEntity>(
    table: IdTable<String>,
    entityType: Class<E>? = null,
    entityCtor: ((EntityID<String>) -> E)? = null
) : EntityClass<String, E>(table, entityType, entityCtor)
