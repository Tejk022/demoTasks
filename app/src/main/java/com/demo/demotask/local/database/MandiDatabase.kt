package com.demo.demotask.local.database

import android.content.Context
import androidx.room.*
import com.demo.demotask.local.dao.MandiRecordsDao
import com.demo.demotask.local.model.Records
import com.demo.demotask.utils.RecordsConverter

@Database(entities = [Records::class], version = 1)
@TypeConverters(RecordsConverter::class)
abstract class MandiDatabase : RoomDatabase() {

    abstract fun getMandiDao(): MandiRecordsDao

    companion object {
        @Volatile
        private var INSTANCE: MandiDatabase? = null

        fun getDatabase(context: Context): MandiDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MandiDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

//        private var INSTANCE: MandiDatabase? = null

//        fun getInstance(context: Context): MandiDatabase {
//            if (INSTANCE == null) {
//                synchronized(MandiDatabase::class) {
//                    INSTANCE = buildRoomDB(context)
//                }
//            }
//            return INSTANCE!!
//        }
//        private fun buildRoomDB(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                MandiDatabase::class.java,
//                "Commodities_Mandi"
//            ).build()

}