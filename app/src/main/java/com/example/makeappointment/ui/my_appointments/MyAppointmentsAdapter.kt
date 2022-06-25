package com.example.makeappointment.ui.my_appointments

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeappointment.R
import com.example.makeappointment.databinding.ItemAppointmentBinding
import com.example.makeappointment.model.Appointment
import com.example.makeappointment.ui.calendar.DateManager

class MyAppointmentsAdapter  : RecyclerView.Adapter<MyAppointmentViewHolder>() {

    private var list = mutableListOf<Appointment>()
    var onAppointmentSelectedListener : OnAppointmentListener? = null

    fun setAppointments(list : List<Appointment>) {
        this.list.clear()
        this.list.addAll(list.toMutableList())
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_appointment, parent, false)
        return MyAppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAppointmentViewHolder, position: Int) {
        val myAppointment = list[position]
        holder.bind(myAppointment)
        onAppointmentSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onAppointmentSelected(myAppointment) }
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}

class MyAppointmentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(appointment: Appointment) {
        val binding = ItemAppointmentBinding.bind(itemView)
        binding.tvDate.text = appointment.start
        binding.tvTime.text = appointment.date
    }
}

