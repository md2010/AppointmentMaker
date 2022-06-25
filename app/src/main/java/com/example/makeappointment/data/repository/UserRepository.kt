package com.example.makeappointment.data.repository

interface UserRepository {

    fun loadData()
    fun logIn(email : String, password : String) : Boolean
    fun logOut()
}