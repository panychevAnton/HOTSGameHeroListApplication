package com.panychev.dotaheroinfoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.panychev.dotaheroinfoapp.data.db.entity.Hero

@Dao
interface DaoHeroList {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertHeroList(heroes: List<Hero>)

    @Query("select * from table_heroes")
    fun getHeroList(): LiveData<List<Hero>>

    @Query("select * from table_heroes where name = :name")
    fun getHeroByName(name: String): LiveData<Hero>
}