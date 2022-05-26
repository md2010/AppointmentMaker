package com.example.makeappointment.ui.new_appointment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.makeappointment.R
import com.example.makeappointment.databinding.FragmentAppointmentsListBinding
import com.example.makeappointment.ui.log_in.LogInFragment

class AppointmentsListFragment : Fragment() {

    private lateinit var binding: FragmentAppointmentsListBinding
    private val args: AppointmentsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentsListBinding.inflate(layoutInflater)
        setUpUI()
        return binding.root
    }

    private fun setUpUI() {
        val date = args.date
        binding.tvDate.text = date[0].toString() + "." + date[1].toString() + "." + date[2].toString() + "."
        binding.btnBack.setOnClickListener( { showCalendarFragment() })
    }

    private fun showCalendarFragment() {
        val action = AppointmentsListFragmentDirections.actionAppointmentsListFragmentToCalendarFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "AppointmentsList"

        fun create(): Fragment {
            return AppointmentsListFragment()
        }

    }
}