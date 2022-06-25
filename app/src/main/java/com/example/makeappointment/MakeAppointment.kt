package com.example.makeappointment

import android.app.Application

class MakeAppointment : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: Application
    }
}