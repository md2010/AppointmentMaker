package com.example.makeappointment.model

data class User(
        val firstName : String,
        val lastName : String,
        val email : String,
        val password : String,
        var uuid : String = ""
)
{
        constructor() : this("", "", "", "", "")
}
