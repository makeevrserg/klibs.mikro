package ru.astrainteractive.klibs.mikro.exposed.entity

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.Entity

@Suppress("UnnecessaryAbstractClass")
public abstract class StringEntity(id: EntityID<String>) : Entity<String>(id)
