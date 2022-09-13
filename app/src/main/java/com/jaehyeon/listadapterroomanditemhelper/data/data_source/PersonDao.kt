package com.jaehyeon.listadapterroomanditemhelper.data.data_source

import androidx.room.*
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import kotlinx.coroutines.flow.Flow

/**
 * Created by Jaehyeon on 2022/09/13.
 */
@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun getPersons(): Flow<List<Person>>

    @Query("SELECT * FROM Person WHERE id = :id")
    suspend fun getPersonById(id: Int): Person?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: Person)

    @Delete
    suspend fun deletePerson(person: Person)
}