package com.jaehyeon.listadapterroomanditemhelper.di

import android.app.Application
import androidx.room.Room
import com.jaehyeon.listadapterroomanditemhelper.data.data_source.PersonDatabase
import com.jaehyeon.listadapterroomanditemhelper.data.repositoryimpl.PersonRepositoryImpl
import com.jaehyeon.listadapterroomanditemhelper.domain.repository.PersonRepository
import com.jaehyeon.listadapterroomanditemhelper.domain.use_case.PersonUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePersonDataBase(app: Application): PersonDatabase {
        return Room.databaseBuilder(
            app,
            PersonDatabase::class.java,
            PersonDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePersonRepository(db: PersonDatabase): PersonRepository {
        return PersonRepositoryImpl(db.personDao)
    }

    @Provides
    @Singleton
    fun providePersonUseCase(repository: PersonRepository): PersonUseCases {
        return PersonUseCases(repository)
    }
}