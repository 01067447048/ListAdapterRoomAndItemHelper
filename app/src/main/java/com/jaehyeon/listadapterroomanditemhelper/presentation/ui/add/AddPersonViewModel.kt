package com.jaehyeon.listadapterroomanditemhelper.presentation.ui.add

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.domain.use_case.PersonUseCases
import com.jaehyeon.listadapterroomanditemhelper.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/09/14.
 */
@HiltViewModel
class AddPersonViewModel @Inject constructor(
    private val useCases: PersonUseCases
): ViewModel() {

    private val _state = MutableLiveData<Event<Unit>>()
    val state: LiveData<Event<Unit>> get() = _state

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> get() = _message

    fun addPerson(person: Person) {
        viewModelScope.launch {
            try {
                useCases.addPerson(person)
                _state.postValue(Event(Unit))
            } catch (t: Throwable) {
                Log.e(javaClass.simpleName, "addPerson: ${t.localizedMessage}", )
                _message.postValue(Event(t.localizedMessage ?: "알 수 없는 에러."))
            }
        }
    }
}