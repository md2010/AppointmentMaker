package com.example.makeappointment.ui

import android.app.AlertDialog
import android.content.Context

class AlertDialogs(var context: Context) {

    private lateinit var alertDialog: AlertDialog.Builder

    fun createBreakDialog() {
       alertDialog = AlertDialog.Builder(context)
       alertDialog.setTitle("Break")
       alertDialog.setMessage("This is time for break, you can't make an appointment.")
       alertDialog.setNeutralButton(
               "OK"
       ) { _, _ -> }
    }

    fun createConfirmDialog() : AlertDialog.Builder {
        alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Confirm or cancel")
        alertDialog.setMessage("Do you want to make an appointment?")
        //positive button in fragment
        alertDialog.setNegativeButton(
                "No"
        ) { _, _ -> }
       return alertDialog
    }

    fun createInvalidDateDialog(){
        alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Invalid Date")
        alertDialog.setMessage("This date is unavailable for making appointments.")
        alertDialog.setNeutralButton(
                "OK"
        ) { _, _ -> }
    }

    fun createDeleteDialog() : AlertDialog.Builder {
        alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Cancellation")
        alertDialog.setMessage("Do you want to cancel an appointment?")
        //positive button in fragment
        alertDialog.setNegativeButton(
                "No"
        ) { _, _ -> }
        return alertDialog
    }

    fun showAlertDialog(){
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }

    companion object {
        val Tag = "AlertDialog"

        fun create(context: Context): AlertDialogs {
            return AlertDialogs(context)
        }
    }
}