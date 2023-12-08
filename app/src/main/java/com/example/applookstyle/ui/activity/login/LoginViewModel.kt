package com.example.applookstyle.ui.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applookstyle.data.model.login.LoginDTO
import com.example.applookstyle.domain.repository.RepositoryLookStyle
import com.example.cronodepro.core.Resource
import com.example.shinyapplication.domain.model.login.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repositoryLookStyle: RepositoryLookStyle) :
    ViewModel() {
    val authLogin = MutableLiveData<Resource<LoginDTO>>()
    fun getAuthLogin(login: Login) {
        authLogin.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            authLogin.postValue(repositoryLookStyle.getAuthLogin(login))
        }
    }
}
