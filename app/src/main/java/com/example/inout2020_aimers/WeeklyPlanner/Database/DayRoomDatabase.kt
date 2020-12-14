package com.example.inout2020_aimers.WeeklyPlanner.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Day::class], version = 2)
abstract class DayRoomDatabase : RoomDatabase() {

    abstract fun dayDao(): DayDao

    companion object {
        @Volatile
        private var INSTANCE: DayRoomDatabase? = null

        fun getDatabase(context: Context): DayRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DayRoomDatabase::class.java,
                    "Day_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}