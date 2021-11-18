package com.example.worldfootball.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [jogos::class], version = 1, exportSchema = false)
abstract class jogosDatabase: RoomDatabase() {

    abstract fun jogoDao(): JogoDao

    companion object{
        @Volatile
        private var INSTANCE: jogosDatabase? = null

        fun getDatabase(context: Context): jogosDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    jogosDatabase::class.java,
                    "user_database"

                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}