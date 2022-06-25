package com.example.makeappointment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.makeappointment.R
import com.example.makeappointment.data.repository.AppointmentRepository
import com.example.makeappointment.data.repository.AppointmentRepositoryImpl
import com.example.makeappointment.data.repository.UserRepository
import com.example.makeappointment.data.repository.UserRepositoryImpl
import com.example.makeappointment.databinding.FragmentHomeBinding
import com.example.makeappointment.di.AppointmentRepositoryFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.*

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var map : GoogleMap
    private var secondClick = 0
    private val userRepository : UserRepository = UserRepositoryImpl()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setUpUI()
        return binding.root
    }

    private fun setUpUI() {
        val mapFragment =  childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        binding.btnLocation.setOnClickListener {
            if (mapFragment != null) {
                childFragmentManager.beginTransaction().show(mapFragment).commit()
                binding.btnMakeAppointment.visibility = View.INVISIBLE
                binding.btnMyAppoinmtments.visibility = View.INVISIBLE
            };
            secondClick++
            if (secondClick % 2 == 0) {
                if (mapFragment != null) {
                    childFragmentManager.beginTransaction().hide(mapFragment).commit()
                    binding.btnMakeAppointment.visibility = View.VISIBLE
                    binding.btnMyAppoinmtments.visibility = View.VISIBLE
                };
            }
        }
        if (mapFragment != null) {
            childFragmentManager.beginTransaction().hide(mapFragment).commit()
        };
        mapFragment?.getMapAsync(this)

        binding.btnMakeAppointment.setOnClickListener{ showCalendarFragment() }
        binding.btnLogOut.setOnClickListener { logOut() }
        binding.btnMyAppoinmtments.setOnClickListener { showMyAppointmentsFragment() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val osijek = LatLng(45.55111, 18.69389)
        map.addMarker(MarkerOptions().position(osijek).title("Doctor's location"))
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLng(osijek))
    }

    private fun logOut() {
        userRepository.logOut()
        val action = HomeFragmentDirections.actionHomeFragmentToLogInFragment()
        findNavController().navigate(action)
    }

    private fun showCalendarFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToCalendarFragment()
        findNavController().navigate(action)
    }

    private fun showMyAppointmentsFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToMyAppointmentsFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "Home"

        fun create(): Fragment {
            return HomeFragment()
        }
    }
}