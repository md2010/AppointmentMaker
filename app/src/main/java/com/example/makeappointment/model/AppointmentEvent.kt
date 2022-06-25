package com.example.makeappointment.model

data class AppointmentEvent(
        var dayOfWeek : Int,
        val startTime : String,
        val endTime : String,
        val status : String,
        var uuid: String = ""
)
{
    constructor() : this(0, "","", "", "") //needed when getting datasnapshot as object
}
