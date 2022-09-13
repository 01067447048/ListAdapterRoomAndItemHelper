package com.jaehyeon.listadapterroomanditemhelper.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.domain.use_case.PersonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jaehyeon on 2022/09/14.
 */
@HiltViewModel
class DetailPersonViewModel @Inject constructor(
    private val useCases: PersonUseCases
): ViewModel() {

    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> get() = _person

    fun getPerson(id: Int) {
        viewModelScope.launch {
            _person.postValue(useCases.getPerson(id))
        }
    }

}