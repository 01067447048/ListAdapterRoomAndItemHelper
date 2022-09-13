package com.jaehyeon.listadapterroomanditemhelper.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person

/**
 * Created by Jaehyeon on 2022/09/13.
 */
@Database(
    entities = [Person::class],
    version = 1
)
abstract class PersonDatabase: RoomDatabase() {

    abstract val personDao: PersonDao

    companion object {
        const val DATABASE_NAME = "person"
    }
}