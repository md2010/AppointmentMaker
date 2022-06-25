package com.example.makeappointment.model

enum class DayOfWeek () {
    Monday, Tuesday, Wednesday, Thursday, Friday;

    companion object {
        fun toInt(day : String) : Int {
            when(day) {
                "MONDAY" -> return 1
                "TUESDAY" -> return 2
                "WEDNESDAY" -> return 1
                "THURSDAY" -> return 2
                "FRIDAY" -> return 1
            }
            return 0
        }
    }

}
