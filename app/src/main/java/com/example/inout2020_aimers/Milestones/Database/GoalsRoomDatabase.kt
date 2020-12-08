package com.example.inout2020_aimers.Milestones.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Goals::class], version = 1)
abstract class GoalsRoomDatabase: RoomDatabase() {

    abstract fun goalsDao(): GoalsDao

    companion object {
        @Volatile
        private var INSTANCE: GoalsRoomDatabase? = null

        fun getDatabase(context: Context): GoalsRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalsRoomDatabase::class.java,
                    "Goals_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}