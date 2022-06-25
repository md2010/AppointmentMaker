package com.example.makeappointment.ui.my_appointments

import com.example.makeappointment.model.Appointment
import com.example.makeappointment.model.AppointmentEvent

interface OnAppointmentListener {

    fun onAppointmentSelected(appointment: Appointment?)
}