package com.example.makeappointment.ui.new_appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.makeappointment.databinding.FragmentCalendarBinding
import java.util.*

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        binding.calendarView.setOnDateChangeListener { calendarView, year: Int, month: Int, dayOfMonth: Int ->
            val calender: Calendar = Calendar.getInstance()
            // Set attributes in calender object as per selected date.
            calender.set(year, month, dayOfMonth)
            // Now set calenderView with this calender object to highlight selected date on UI.
            calendarView.setDate(calender.timeInMillis, true, true)
            val date = intArrayOf(dayOfMonth, month, year)
            showAppointmentsListFragment(date)
        }
        return binding.root
    }

    private fun showAppointmentsListFragment(date: IntArray){
        val action = CalendarFragmentDirections.actionCalendarFragmentToAppointmentsListFragment(date)
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "Calendar"

        fun create(): Fragment {
            return CalendarFragment()
        }
    }
}