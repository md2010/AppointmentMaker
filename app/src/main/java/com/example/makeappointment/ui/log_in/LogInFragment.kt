package com.example.makeappointment.ui.log_in

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.makeappointment.data.repository.UserRepositoryImpl
import com.example.makeappointment.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding
    private val userRepository : UserRepositoryImpl = UserRepositoryImpl()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentLogInBinding.inflate(layoutInflater)
        setUpUI()
        return binding.root
    }

    private fun setUpUI() {
        binding.btnLogIn.setOnClickListener { checkLogIn() }
    }

    private fun checkLogIn() {
        val e = binding.etUserName.text.toString()
            userRepository.logIn(e, object : MyOnCallback {
                override fun onCallback(email: String?, password: String?) {
                    val p = binding.etPassword.text.toString()
                    if(e == email && p == password){
                        Log.d(TAG, "success")
                        logInSuccess()
                        return
                    } else {
                        Log.d(TAG, "failure")
                        logInFailure()
                    }
                }
            })
    }

    private fun logInSuccess() {
        val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment()
        findNavController().navigate(action)
    }

    private fun logInFailure() {
        Toast.makeText(this.context, "Log In Failed", Toast.LENGTH_SHORT).show()
    }

    companion object {
        val Tag = "LogIn"

        fun create(): Fragment {
            return LogInFragment()
        }
    }

}