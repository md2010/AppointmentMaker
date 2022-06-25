package com.example.makeappointment.di

import android.app.Application
import com.example.makeappointment.MakeAppointment
import com.example.makeappointment.data.repository.AppointmentRepository
import com.example.makeappointment.data.repository.AppointmentRepositoryImpl
import com.example.makeappointment.data.room.AppointmentDatabase

object AppointmentRepositoryFactory {

    val roomDb = AppointmentDatabase.getDatabase(MakeAppointment.instance)
    val appointmentRepository: AppointmentRepository = AppointmentRepositoryImpl(roomDb.getAppointmentDao())
}