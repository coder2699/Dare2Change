package com.example.inout2020_aimers.appblock.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BlockedApps::class], version = 1)
abstract class BlockerDatabase : RoomDatabase() {

    abstract fun getAppsDao() : AppsDao

    companion object{

        private var instance : BlockerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            BlockerDatabase::class.java,
                "apps_database"
                ).build()


    }


}