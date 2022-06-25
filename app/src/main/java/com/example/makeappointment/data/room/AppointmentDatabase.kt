package com.example.makeappointment.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.makeappointment.data.repository.AppointmentDao
import com.example.makeappointment.model.Appointment

@Database(
    entities = [Appointment::class],
    version = 1,
    exportSchema = false
)

abstract class AppointmentDatabase : RoomDatabase() {

    abstract fun getAppointmentDao(): AppointmentDao

    companion object {

        private const val databaseName = "notesDb"

        @Volatile
        private var INSTANCE: AppointmentDatabase? = null

        fun getDatabase(context: Context): AppointmentDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppointmentDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppointmentDatabase::class.java,
                databaseName
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }
}