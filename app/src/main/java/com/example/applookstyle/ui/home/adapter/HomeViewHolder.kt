package com.example.applookstyle.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.applookstyle.data.model.appointment.Data
import com.example.applookstyle.databinding.ItemAppoinmentsBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeViewHolder(private val binding: ItemAppoinmentsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Data) {
        val input = item.appointment_date
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val dateTime = LocalDateTime.parse(input, formatter)

        val dayOfWeek = dateTime.dayOfWeek
        val dayOfMonth = dateTime.dayOfMonth
        val time = dateTime.format(DateTimeFormatter.ofPattern("hh:mm a"))


        println("Día de la semana: $dayOfWeek")
        println("Número del día: $dayOfMonth")
        println("Hora: $time")

        binding.tvHour.text = time
        binding.tvDay.text = dayOfMonth.toString()
        binding.tvTextDay.text = dayOfWeek.toString()

    }

}
