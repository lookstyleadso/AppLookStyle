package com.example.cronodepro.data.services

import com.example.applookstyle.data.model.appointment.AppointmentDTO
import com.example.applookstyle.data.model.login.LoginDTO
import com.example.shinyapplication.domain.model.login.Login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LookStyleServices {

    @POST("auth/authenticate")
    suspend fun getAuthLogin(@Body login: Login): Response<LoginDTO>

    @GET("appointments")
    suspend fun getAppointmentsAll(@Header("Authorization") jwt: String): Response<AppointmentDTO>

}