package com.test

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import java.util.*

@Serdeable
@MappedEntity
data class Document(
    val firstName: String,
    val lastName: String,

    @field:Id
    val id: String = UUID.randomUUID().toString()
)