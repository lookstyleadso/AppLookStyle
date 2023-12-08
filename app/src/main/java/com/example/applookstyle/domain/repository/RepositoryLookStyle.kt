package com.example.applookstyle.domain.repository

import com.example.applookstyle.data.model.appointment.Data
import com.example.applookstyle.data.model.login.LoginDTO
import com.example.cronodepro.core.Resource
import com.example.cronodepro.data.services.LookStyleServices
import com.example.shinyapplication.domain.model.login.Login
import javax.inject.Inject

class RepositoryLookStyle @Inject constructor(private val lookStyleServices: LookStyleServices) {
    suspend fun getAuthLogin(login: Login): Resource<LoginDTO> {
        try {
            val res = lookStyleServices.getAuthLogin(login = login)
            if (res.isSuccessful) {
                val body = res.body()
                body?.let {
                    return Resource.Success(body)
                }
            }
            return  Resource.Error("Error in the service")
        }catch (e:Exception){
            return  Resource.Error("Error: {${e.message}}")
        }
    }

    suspend fun getAppointment(token: String): Resource<List<Data>> {
        try {
            val res = lookStyleServices.getAppointmentsAll(jwt = token)
            if (res.isSuccessful) {
                val body = res.body()
                body?.let {
                    return Resource.Success(body.data)
                }
            }
            return  Resource.Error("Error in the service")
        }catch (e:Exception){
            return  Resource.Error("Error: {${e.message}}")
        }
    }


}