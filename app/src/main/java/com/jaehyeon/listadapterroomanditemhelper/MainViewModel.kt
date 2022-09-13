package com.jaehyeon.listadapterroomanditemhelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.domain.use_case.PersonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/09/14.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCases: PersonUseCases
): ViewModel() {

    private val _state = MutableStateFlow<List<Person>>(emptyList())
    val state: StateFlow<List<Person>> get() = _state


    fun getPersons() {
        useCases.getPersons().onEach {
            _state.value = it
        }.launchIn(viewModelScope)
    }

    fun deletePersons(person: Person) {
        viewModelScope.launch {
            launch { useCases.deletePerson(person) }.join()
            getPersons()
        }
    }

    fun updatePersons(person: Person) {
        viewModelScope.launch {
            launch { useCases.addPerson(person) }.join()
            getPersons()
        }
    }

}