package com.example.makeappointment.ui.appointments_list

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.makeappointment.MakeAppointment
import com.example.makeappointment.data.repository.AppointmentEventRepositoryImpl
import com.example.makeappointment.data.repository.UserRepositoryImpl
import com.example.makeappointment.databinding.FragmentAppointmentsListBinding
import com.example.makeappointment.di.AppointmentRepositoryFactory
import com.example.makeappointment.model.Appointment
import com.example.makeappointment.model.AppointmentEvent
import com.example.makeappointment.notification.NotificationHelper
import com.example.makeappointment.notification.notificationID
import com.example.makeappointment.ui.AlertDialogs
import com.example.makeappointment.ui.calendar.DateManager
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import kotlin.collections.ArrayList


class AppointmentsListFragment : Fragment(), OnAppointmentEventListener {

    private lateinit var binding: FragmentAppointmentsListBinding
    private lateinit var adapter : AppointmentsAdapter
    private val args: AppointmentsListFragmentArgs by navArgs()
    private val appointmentEventRepository = AppointmentEventRepositoryImpl()
    private var appointmentRepository = AppointmentRepositoryFactory.appointmentRepository
    private val userRepository = UserRepositoryImpl()
    private var list : ArrayList<AppointmentEvent> = arrayListOf<AppointmentEvent>()
    private lateinit var date : String
    private var notificationHelper = NotificationHelper()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppointmentsListBinding.inflate(layoutInflater)
        setUpUI()
        setUpRecyclerView()
        return binding.root
    }

    private fun getAppointmentEventsByDayOfWeek(dayOfWeek: Int) {
        appointmentEventRepository.dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) { //when done list is empty (asynchronous fun) so needs to be filled here in this fragment and copied to adapter's list
                for (ds: DataSnapshot in snapshot.children) { //list of all objects
                    for (s: DataSnapshot in ds.children) { //get each object
                        val appointment = s.getValue(AppointmentEvent::class.java)
                        if (appointment != null && !appointmentRepository.checkAvailability(date, appointment.uuid) && appointment.dayOfWeek == dayOfWeek) {
                            list.add(appointment)
                        }
                    }
                }
                adapter.setAppointments(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.toString())
            }
        })
    }

    private fun setUpRecyclerView() {
        binding.recView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
        )
        getAppointmentEventsByDayOfWeek(args.date[0])
        adapter = AppointmentsAdapter()
        adapter.onAppointmentEventSelectedListener = this
        binding.recView.adapter = adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpUI() {
        val dateManager = DateManager()
        date = dateManager.myToString(LocalDate.of(args.date[3], args.date[2], args.date[1]))
        binding.tvDate.text = date
        //appointmentEventRepository.loadData()  //call only first time to store in DB
        binding.btnBack.setOnClickListener { showCalendarFragment() }
    }

    private fun showCalendarFragment() {
        val action = AppointmentsListFragmentDirections.actionAppointmentsListFragmentToCalendarFragment()
        findNavController().navigate(action)
        parentFragmentManager.beginTransaction().remove(this).commit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onAppointmentEventSelected(appointmentEvent: AppointmentEvent?) {
        if(appointmentEvent?.status == "break") {
            val alertDialog = AlertDialogs(requireActivity())
            alertDialog.createBreakDialog()
            alertDialog.showAlertDialog()
        } else {
            val alertDialog = AlertDialogs(requireActivity())
            val alertDialogConfirm = alertDialog.createConfirmDialog()
            alertDialogConfirm.setPositiveButton(
                    "yes"
            ) { _, _ ->
                saveNewAppointment(appointmentEvent)
                scheduleNotification()
            }
            alertDialog.showAlertDialog()
        }
    }

     @RequiresApi(Build.VERSION_CODES.O)
     private fun saveNewAppointment(appointmentEvent: AppointmentEvent?) {
        appointmentEvent?.let { it ->
            appointmentRepository.save(Appointment(date, it.startTime, it.endTime, it.uuid, userRepository.authorizedUser)) //userRepository.authorizedUser
            scheduleNotification()
        }
        val action = AppointmentsListFragmentDirections.actionAppointmentsListFragmentToCalendarFragment()
        findNavController().navigate(action)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scheduleNotification() {
        val context = MakeAppointment.instance
        notificationHelper.createNotificationChannel(context)
        val intent = Intent(context, NotificationHelper::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
                context,
                notificationID,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar = Calendar.getInstance()
        //calendar.set(2022,5,25,15,56,0) testing
        calendar.set(args.date[1], args.date[2] - 1, args.date[3] , 12,0 ,0) //for real time
        val date = Date(calendar.timeInMillis)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

}