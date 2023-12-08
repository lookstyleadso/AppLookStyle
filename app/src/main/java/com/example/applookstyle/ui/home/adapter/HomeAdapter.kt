package com.example.applookstyle.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applookstyle.data.model.appointment.Data
import com.example.applookstyle.databinding.ItemAppoinmentsBinding

class HomeAdapter(var listAppointment: List<Data>) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemAppoinmentsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = listAppointment.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = listAppointment[position]
        holder.bind(item)
    }

    fun updateList(newList: List<Data>) {
        listAppointment = newList
        notifyDataSetChanged()
    }
}