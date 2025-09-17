package ru.astrainteractive.klibs.mikro.exposed.dao

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable

@Suppress("UnnecessaryAbstractClass")
abstract class StringEntity(id: EntityID<String>) : Entity<String>(id)

@Suppress("UnnecessaryAbstractClass")
abstract class StringEntityClass<out E : StringEntity>(
    table: IdTable<String>,
    entityType: Class<E>? = null,
    entityCtor: ((EntityID<String>) -> E)? = null
) : EntityClass<String, E>(table, entityType, entityCtor)
