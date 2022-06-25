package com.example.makeappointment.ui.appointments_list

import com.example.makeappointment.model.AppointmentEvent

interface OnAppointmentEventListener {

    fun onAppointmentEventSelected(appointmentEvent: AppointmentEvent?)
}