package com.example.makeappointment.data.repository

import androidx.room.*
import com.example.makeappointment.model.Appointment

@Dao
interface AppointmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(app: Appointment)

    @Query("DELETE FROM appointments")
    fun deleteAll()

    @Delete
    fun delete(app: Appointment)

    @Query("SELECT EXISTS (SELECT 1 FROM appointments WHERE date = :date AND appointmentEvent_id = :appointmentEventId)") //true if exists -> not available!
    fun checkAvailability(date : String, appointmentEventId : String): Boolean

    @Query("SELECT * FROM appointments WHERE user_id = :user_id")
    fun getUsersAppointments(user_id : String): List<Appointment>
}