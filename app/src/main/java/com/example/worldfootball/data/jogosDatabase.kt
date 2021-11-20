package com.example.worldfootball.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.AutoMigration

@Database(entities = [jogos::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration (from = 1, to = 2)]
)
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