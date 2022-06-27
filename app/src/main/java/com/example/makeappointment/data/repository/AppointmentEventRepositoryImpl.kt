package com.example.makeappointment.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.makeappointment.model.AppointmentEvent
import com.google.firebase.database.*

class AppointmentEventRepositoryImpl : AppointmentEventRepository {

    val dbReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("appointmentEvents")

    override fun loadData() {
        val appEvents: List<AppointmentEvent> = mutableListOf(
                AppointmentEvent(1, "7:00", "7:20", "available"),
                AppointmentEvent(1, "7:20", "7:40", "available"),
                AppointmentEvent(1, "7:40", "8:00", "available"),
                AppointmentEvent(1, "8:00", "8:20", "available"),
                AppointmentEvent(1, "8:20", "8:40", "available"),
                AppointmentEvent(1, "8:40", "9:00", "available"),
                AppointmentEvent(1, "9:00", "9:20", "available"),
                AppointmentEvent(1, "9:20", "9:40", "available"),
                AppointmentEvent(1, "9:40", "10:00", "available"),
                AppointmentEvent(1, "10:00", "10:40", "break"), //pauza
                AppointmentEvent(1, "10:40", "11:00", "available"),
                AppointmentEvent(1, "11:00", "11:20", "available"),
                AppointmentEvent(1, "11:20", "11:40", "available"),
                AppointmentEvent(1, "11:40", "12:00", "available"),
                AppointmentEvent(1, "12:00", "12:20", "available"),
                AppointmentEvent(1, "12:20", "12:40", "available"),
                AppointmentEvent(1, "12:40", "13:00", "available"),

                AppointmentEvent(2, "14:00", "14:20", "available"),
                AppointmentEvent(2, "14:20", "14:40", "available"),
                AppointmentEvent(2, "14:40", "15:00", "available"),
                AppointmentEvent(2, "15:00", "15:20", "available"),
                AppointmentEvent(2, "15:20", "15:40", "available"),
                AppointmentEvent(2, "15:40", "16:00", "available"),
                AppointmentEvent(2, "16:00", "16:20", "available"),
                AppointmentEvent(2, "16:20", "16:40", "available"),
                AppointmentEvent(2, "16:40", "17:00", "available"),
                AppointmentEvent(2, "17:00", "17:40", "break"), //pauza
                AppointmentEvent(2, "17:40", "18:00", "available"),
                AppointmentEvent(2, "18:00", "18:20", "available"),
                AppointmentEvent(2, "18:20", "18:40", "available"),
                AppointmentEvent(2, "18:40", "19:00", "available"),
                AppointmentEvent(2, "19:00", "19:20", "available"),
                AppointmentEvent(2, "19:20", "19:40", "available"),
                AppointmentEvent(2, "19:40", "20:00", "available"),
        )
        appEvents.forEach {
            val key = dbReference.child("appointmentEvents").push().key
            if (key != null) {
                it.uuid = key
            }
            if (key != null) {
                dbReference.child("appointmentEvents").child(key).setValue(it)
            }
        }
    }

    override fun delete(){
        dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (s in dataSnapshot.children) {
                    s.ref.removeValue()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException())
            }
        })
    }

}