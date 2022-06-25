package com.example.makeappointment.data.repository

import androidx.room.Delete
import androidx.room.Query
import com.example.makeappointment.model.Appointment

interface AppointmentRepository {

    fun save(app: Appointment)
    fun delete(app: Appointment)
    fun deleteAll()
    fun checkAvailability(date : String, appointmentEventId : String): Boolean
    fun getUsersAppointments(user_id : String): List<Appointment>
}