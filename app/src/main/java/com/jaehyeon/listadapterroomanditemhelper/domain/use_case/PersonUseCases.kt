package com.jaehyeon.listadapterroomanditemhelper.domain.use_case

import com.jaehyeon.listadapterroomanditemhelper.data.entity.InvalidPersonException
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/09/13.
 */
class PersonUseCases @Inject constructor(
    private val repository: PersonRepository
){
    fun getPersons(): Flow<List<Person>> = repository.getPersons().map { persons ->
        persons.sortedWith(compareByDescending<Person> { it.mark }.thenBy { it.name }.thenBy { it.birth })
    }

    suspend fun getPerson(id: Int): Person? {
        return repository.getPersonById(id)
    }

    suspend fun addPerson(person: Person) {
        if (person.name.isBlank()) throw InvalidPersonException("Name can't be empty")

        if (person.birth.isBlank()) throw InvalidPersonException("Birth can't be empty")

        if (person.birth.length != 8) throw InvalidPersonException("Birth have to be 8")

        repository.insertPerson(person)
    }

    suspend fun deletePerson(person: Person) {
        repository.deletePerson(person)
    }
}