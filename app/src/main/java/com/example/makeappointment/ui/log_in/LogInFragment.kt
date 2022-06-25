package com.example.makeappointment.ui.log_in

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.makeappointment.data.repository.UserRepository
import com.example.makeappointment.data.repository.UserRepositoryImpl
import com.example.makeappointment.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private val userRepository : UserRepository = UserRepositoryImpl()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentLogInBinding.inflate(layoutInflater)
        setUpUI()
        return binding.root
    }

    private fun setUpUI() {
        val email = binding.etUserName.text.toString()
        val password = binding.etPassword.text.toString()
        binding.btnLogIn.setOnClickListener(View.OnClickListener { checkLogIn(email, password) })
    }

    private fun checkLogIn(email: String, password: String) {
        if(userRepository.logIn(email, password)) {
            Toast.makeText(this.context, "Login Successful..", Toast.LENGTH_SHORT).show()
            val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment()
            findNavController().navigate(action)
        } else {
            Toast.makeText(this.context, "Login Failed..", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        val Tag = "LogIn"

        fun create(): Fragment {
            return LogInFragment()
        }
    }
}