package com.panychev.dotaheroinfoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.panychev.dotaheroinfoapp.data.db.entity.HeroAPI

@Database(
    entities = [HeroAPI::class],
    version = 1
)
abstract class HeroListDatabase: RoomDatabase() {
    abstract fun heroListDao(): HeroListDao
    companion object{
        @Volatile
        private var instance: HeroListDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDataBase(context).also { instance = it }
        }
        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                HeroListDatabase::class.java, "heroes.db")
                .build()
    }
}