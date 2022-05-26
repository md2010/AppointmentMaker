package com.example.makeappointment.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.makeappointment.R
import com.example.makeappointment.databinding.FragmentHomeBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.makeappointment.ui.new_appointment.CalendarFragment
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var map : GoogleMap
    private var secondClick = 0

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
        binding.btnLocation.setOnClickListener({
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
        })
        if (mapFragment != null) {
            childFragmentManager.beginTransaction().hide(mapFragment).commit()
        };
        mapFragment?.getMapAsync(this)

        binding.btnMakeAppointment.setOnClickListener{ showNewAppointmentFragment() }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val osijek = LatLng(45.55111, 18.69389)
        map.addMarker(MarkerOptions().position(osijek).title("Marker in Osijek"))
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLng(osijek))
    }

    private fun showNewAppointmentFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToCalendarFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "Home"

        fun create(): Fragment {
            return HomeFragment()
        }
    }
}