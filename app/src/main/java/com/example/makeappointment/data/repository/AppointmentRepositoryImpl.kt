package com.example.makeappointment.data.repository

import com.example.makeappointment.model.Appointment

class AppointmentRepositoryImpl(val appointmentDao: AppointmentDao) : AppointmentRepository {

    override fun save(app: Appointment) = appointmentDao.save(app)
    override fun delete(app: Appointment) = appointmentDao.delete(app)
    override fun deleteAll() = appointmentDao.deleteAll()
    override fun checkAvailability(date: String, appointmentEventId: String) = appointmentDao.checkAvailability(date, appointmentEventId)
    override fun getUsersAppointments(user_id: String): List<Appointment> = appointmentDao.getUsersAppointments(user_id)
}