package com.jaehyeon.listadapterroomanditemhelper.data.repositoryimpl

import com.jaehyeon.listadapterroomanditemhelper.data.data_source.PersonDao
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jaehyeon on 2022/09/13.
 */
class PersonRepositoryImpl(
    private val dao: PersonDao
): PersonRepository {

    override fun getPersons(): Flow<List<Person>> = dao.getPersons()

    override suspend fun getPersonById(id: Int): Person? = dao.getPersonById(id)

    override suspend fun insertPerson(person: Person) = dao.insertPerson(person)

    override suspend fun deletePerson(person: Person) = dao.deletePerson(person)

}