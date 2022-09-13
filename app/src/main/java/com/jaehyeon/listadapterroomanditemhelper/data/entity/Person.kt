package com.jaehyeon.listadapterroomanditemhelper.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val birth: String,
    val mark: Boolean
)

class InvalidPersonException(message: String): Exception(message)