package com.example.makeappointment.data.repository

import com.example.makeappointment.ui.log_in.MyOnCallback

interface UserRepository {

    fun loadData()
    fun logIn(e : String, callback : MyOnCallback)
    fun logOut()
}