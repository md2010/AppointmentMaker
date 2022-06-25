package com.example.makeappointment.data.repository

import android.R.attr.password
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.makeappointment.model.User
import com.example.makeappointment.ui.MainActivity
import com.example.makeappointment.ui.log_in.LogInFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class UserRepositoryImpl : UserRepository {

    private val dbReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
    var authorizedUser : String = ""

    override fun loadData() {
        val availableUsers: List<User> = mutableListOf(
            User("Ann", "Smith", "ann@gmail.com", "password"),
            User("Marie", "Loyd", "marie@gmail.com", "password"),
            User("John", "Simpson", "john@gmail.com", "password"),
            User("Ed", "Cream", "ed@gmail.com", "password")
        )
        availableUsers.forEach {
            val key = dbReference.child("users").push().key
            it.uuid = key
            if (key != null) {
                dbReference.child("users").child(key).setValue(it)
            }
        }
    }

    override fun logIn(email : String, password : String) : Boolean {
        var e: String = ""
        var p: String = ""
        var id : String = ""
        dbReference.child(email).get().addOnSuccessListener {
            if (it.exists()) {
                e = it.child("email").value.toString()
                p = it.child("password").value.toString()
                id = it.child("uuid").value.toString()
            }
        }
        if (e == email && password == p) {
            authorizedUser = id
            return true
        } else {
            return false
        }
    }

    override fun logOut() {
        authorizedUser = ""
    }
}