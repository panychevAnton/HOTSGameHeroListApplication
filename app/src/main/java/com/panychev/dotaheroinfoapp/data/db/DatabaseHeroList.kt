package com.panychev.dotaheroinfoapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.panychev.dotaheroinfoapp.data.db.converters.TypeConverterTalentList
import com.panychev.dotaheroinfoapp.data.db.entity.Hero
import com.panychev.dotaheroinfoapp.data.network.response.Talent

@Database(
    entities = [Hero::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(TypeConverterTalentList::class)
abstract class DatabaseHeroList: RoomDatabase() {
    abstract fun heroListDao(): DaoHeroList
    companion object{
        @Volatile
        private var instance: DatabaseHeroList? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDataBase(context).also { instance = it }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                DatabaseHeroList::class.java, "heroes.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}