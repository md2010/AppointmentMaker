package com.example.makeappointment.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class Appointment (
        @ColumnInfo(name = "date")
        var start : String,
        @ColumnInfo(name = "start")
        var end : String,
        @ColumnInfo(name = "end")
        var date : String,
        @ColumnInfo(name = "appointmentEvent_id")
        var appointmentEventId : String,
        @ColumnInfo(name = "user_id")
        var userId : String,
        @PrimaryKey(autoGenerate = true)
        var id : Long = 0
        )
{}
