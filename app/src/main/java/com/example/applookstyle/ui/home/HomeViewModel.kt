package com.example.applookstyle.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applookstyle.data.model.appointment.Data
import com.example.applookstyle.domain.repository.RepositoryLookStyle
import com.example.cronodepro.core.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var repositoryLookStyle: RepositoryLookStyle) :
    ViewModel() {

    val listAppointments = MutableLiveData<Resource<List<Data>>>()
    fun getAppointment(jwt: String) {
        listAppointments.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            listAppointments.postValue(repositoryLookStyle.getAppointment(jwt))
        }
    }
}