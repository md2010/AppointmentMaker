package com.example.makeappointment.ui.appointments_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.makeappointment.R
import com.example.makeappointment.databinding.ItemAppointmentEventBinding
import com.example.makeappointment.model.AppointmentEvent

class AppointmentsAdapter ()  : RecyclerView.Adapter<AppointmentsViewHolder>() {

    var appointmentEvents : MutableList<AppointmentEvent> = mutableListOf()
    var onAppointmentEventSelectedListener : OnAppointmentEventListener? = null

    fun setAppointments(list : ArrayList<AppointmentEvent>) {
        this.appointmentEvents.clear()
        this.appointmentEvents.addAll(list)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_appointment_event, parent, false)
        return AppointmentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        val appointmentEvent = appointmentEvents[position]
        holder.bind(appointmentEvent)
        onAppointmentEventSelectedListener?.let { listener ->
            holder.itemView.setOnClickListener { listener.onAppointmentEventSelected(appointmentEvent) }
        }
    }

    override fun getItemCount(): Int {
        return appointmentEvents.count()
    }
}

class AppointmentsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bind(appointmentEvent: AppointmentEvent) {
        val binding = ItemAppointmentEventBinding.bind(itemView)
        binding.tvApp.text = appointmentEvent.startTime + " - " + appointmentEvent.endTime

        if(appointmentEvent.status == "available")
            binding.tvApp.setBackgroundResource(R.color.background)
        else
            binding.tvApp.setBackgroundResource(R.color.pauze)
    }
}
