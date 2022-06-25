package com.example.makeappointment.ui.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.makeappointment.model.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateManager(private val year: Int,
                  private val month: Int,
                  private val dayOfMonth: Int)
{
    constructor() : this(0, 0, 0)

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkIfDatePassedToday() : Boolean {
        val selectedDate = LocalDate.of(year, month, dayOfMonth)
        val today = LocalDate.now()
        return selectedDate.isAfter(today)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isWorkingDay() : Boolean {
        return convertWeekDay() != 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertWeekDay() : Int {
        //create date from int
        val date = LocalDate.of(year, month, dayOfMonth)
        return DayOfWeek.toInt(date.dayOfWeek.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun myToString(localDate: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return localDate.format(formatter)
    }
}