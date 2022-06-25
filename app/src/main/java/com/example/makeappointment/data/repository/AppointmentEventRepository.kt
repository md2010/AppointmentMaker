package com.example.makeappointment.data.repository

import com.example.makeappointment.model.AppointmentEvent

interface AppointmentEventRepository {

    fun loadData()
    //fun getAppointmentEventsByDayOfWeek(dayOfWeek : Int) async -> in fragment
    fun delete()
}