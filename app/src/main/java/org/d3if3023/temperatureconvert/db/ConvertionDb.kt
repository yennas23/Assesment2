package org.d3if3023.temperatureconvert.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConvertionEntity::class], version = 2, exportSchema = false)
abstract class ConvertionDb : RoomDatabase() {
    abstract val dao: ConvertionDao
    companion object {
        @Volatile
        private var INSTANCE: ConvertionDb? = null
        fun getInstance(context: Context): ConvertionDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConvertionDb::class.java,
                        "celciusconvertion.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}