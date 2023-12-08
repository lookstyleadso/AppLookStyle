package com.example.applookstyle.data.model.appointment

data class Data(
    val BarberId: Int,
    val BarbershopId: Int,
    val UserId: Int,
    val appointment_date: String,
    val appointment_hour: String,
    val aprove: Boolean,
    val createdAt: String,
    val id: Int,
    val state: String,
    val updatedAt: String
)