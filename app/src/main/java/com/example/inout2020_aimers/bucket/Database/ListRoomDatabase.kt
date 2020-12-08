package com.example.inout2020_aimers.bucket.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bucket::class], version = 1)
abstract class ListRoomDatabase: RoomDatabase() {

    abstract fun listDao(): ListDao

    companion object {
        @Volatile
        private var INSTANCE: ListRoomDatabase? = null

        fun getDatabase(context: Context): ListRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListRoomDatabase::class.java,
                    "List_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}