package com.sysaxiom.mvvmbasics.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sysaxiom.mvvmbasics.data.db.entities.*

@Database(
    entities = [Relative::class,RelativeMaster::class,Speciality::class,UserData::class],
    version = 1
)
abstract class MVVMBasicsDatabase: RoomDatabase() {

    abstract fun getAuthDao(): AuthDao

    companion object {
        @Volatile
        private var instance: MVVMBasicsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: createDatabase(
                    context
                ).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                MVVMBasicsDatabase::class.java, "MVVMBasicsDB.db").build()
    }
}