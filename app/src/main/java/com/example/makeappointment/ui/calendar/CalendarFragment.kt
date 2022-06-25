package com.example.makeappointment.ui.calendar

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.makeappointment.databinding.FragmentCalendarBinding
import com.example.makeappointment.ui.AlertDialogs

import java.util.*

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding
    private var calendar : Calendar = GregorianCalendar()
    private lateinit var dateManager : DateManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        binding.btnHome.setOnClickListener { showHomeFragment() }
        setDate()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setDate() {
        binding.calendarView.firstDayOfWeek = 2;

        binding.calendarView.setOnDateChangeListener { calendarView, year: Int, m: Int, dayOfMonth: Int ->
            val month = m+1 //months from 0 to 11
            dateManager = DateManager(year, month, dayOfMonth)

            if ( !dateManager.isWorkingDay() || !dateManager.checkIfDatePassedToday() ) {
                val alertDialog = AlertDialogs(requireActivity())
                alertDialog.createInvalidDateDialog()
                alertDialog.showAlertDialog()
            } else {
                // Set attributes in calender object as per selected date.
                calendar.set(year, month, dayOfMonth)
                calendarView.setDate(calendar.timeInMillis, true, true)

                val dayOfWeek = dateManager.convertWeekDay()
                val date = intArrayOf(dayOfWeek, dayOfMonth, month, year)
                showAppointmentsListFragment(date)
            }
        }
        binding.btnHome.setOnClickListener { showHomeFragment() }
    }

    private fun showAppointmentsListFragment(date: IntArray){
        val action = CalendarFragmentDirections.actionCalendarFragmentToAppointmentsListFragment(date)
        findNavController().navigate(action)
    }

    private fun showHomeFragment(){
        val action = CalendarFragmentDirections.actionCalendarFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "Calendar"

        fun create(): Fragment {
            return CalendarFragment()
        }
    }
}