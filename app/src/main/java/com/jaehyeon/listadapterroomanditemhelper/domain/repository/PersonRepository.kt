package com.jaehyeon.listadapterroomanditemhelper.domain.repository

import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jaehyeon on 2022/09/13.
 */
interface PersonRepository {

    fun getPersons(): Flow<List<Person>>

    suspend fun getPersonById(id: Int): Person?

    suspend fun insertPerson(person: Person)

    suspend fun deletePerson(person: Person)
}