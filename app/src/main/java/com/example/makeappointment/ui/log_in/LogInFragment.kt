package com.example.makeappointment.ui.log_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.makeappointment.R
import com.example.makeappointment.databinding.FragmentLogInBinding
import com.example.makeappointment.ui.home.HomeFragment

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLogInBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        val Tag = "LogIn"

        fun create(): Fragment {
            return LogInFragment()
        }
    }
}