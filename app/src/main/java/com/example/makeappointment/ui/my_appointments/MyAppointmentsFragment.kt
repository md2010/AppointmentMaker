package com.example.makeappointment.ui.my_appointments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makeappointment.MakeAppointment
import com.example.makeappointment.R
import com.example.makeappointment.data.repository.AppointmentRepository
import com.example.makeappointment.data.repository.UserRepositoryImpl
import com.example.makeappointment.databinding.FragmentMyAppointmentsBinding
import com.example.makeappointment.di.AppointmentRepositoryFactory
import com.example.makeappointment.model.Appointment
import com.example.makeappointment.ui.AlertDialogs


class MyAppointmentsFragment : Fragment(), OnAppointmentListener {

    private lateinit var binding: FragmentMyAppointmentsBinding
    private lateinit var adapter : MyAppointmentsAdapter
    private var appointmentRepository = AppointmentRepositoryFactory.appointmentRepository
    private var userRepository = UserRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyAppointmentsBinding.inflate(layoutInflater)
        setUpRecyclerView()
        binding.btnHome.setOnClickListener { showHomeFragment() }
        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.recViewMyAppointments.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = MyAppointmentsAdapter()
        adapter.onAppointmentSelectedListener = this
        binding.recViewMyAppointments.adapter = adapter
        val list = appointmentRepository.getUsersAppointments(userRepository.authorizedUser)
        adapter.setAppointments(list)
    }

    private fun showHomeFragment(){
        val action = MyAppointmentsFragmentDirections.actionMyAppointmentsFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    override fun onAppointmentSelected(appointment: Appointment?) {
        val alertDialogs = AlertDialogs(requireActivity())
        val alertDialog = alertDialogs.createDeleteDialog()
        alertDialog.setPositiveButton(
                "yes"
        ) { _, _ ->
            if (appointment != null) {
                appointmentRepository.delete(appointment)
                val list = appointmentRepository.getUsersAppointments(userRepository.authorizedUser)
                adapter.setAppointments(list)
            }
        }
        alertDialogs.showAlertDialog()
    }

}