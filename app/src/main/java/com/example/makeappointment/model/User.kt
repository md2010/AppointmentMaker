package com.example.makeappointment.model

data class User(
        val FirstName : String? = "",
        val lastName : String? = "",
        val email : String? = "",
        val password : String? = "",
        var uuid : String? = ""
)
{}
