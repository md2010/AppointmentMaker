package com.example.makeappointment.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.makeappointment.model.User
import com.example.makeappointment.ui.log_in.MyOnCallback
import com.google.firebase.database.*


class UserRepositoryImpl : UserRepository {

    val dbReference : DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
    var authorizedUser : String = ""
    var email : String = ""

    override fun loadData() {
        val availableUsers: List<User> = mutableListOf(
            User("Ann", "Smith", "ann@gmail.com", "password"),
            User("Marie", "Loyd", "marie@gmail.com", "password"),
            User("John", "Simpson", "john@gmail.com", "password"),
            User("Ed", "Cream", "ed@gmail.com", "password")
        )
        availableUsers.forEach {
            val key = dbReference.child("users").push().key
            if (key != null) {
                it.uuid = key
            }
            if (key != null) {
                dbReference.child("users").child(key).setValue(it)
            }
        }
    }

    override fun logIn(e : String, callback : MyOnCallback) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(ds in snapshot.children) {
                    for (s: DataSnapshot in ds.children) {
                        val user = s.getValue(User::class.java)
                        if (user != null) {
                            if(user.email.equals(e)) {
                                callback.onCallback(user.email, user.password)
                                break
                            }
                        }
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "Can't fetch data.")
            }
        }
        dbReference.addListenerForSingleValueEvent(listener)
    }

    override fun logOut() {
        authorizedUser = ""
    }
}