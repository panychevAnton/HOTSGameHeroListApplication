package com.panychev.dotaheroinfoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.panychev.dotaheroinfoapp.data.db.entity.HeroAPI
import com.panychev.dotaheroinfoapp.data.db.entity.name

@Dao
interface HeroListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(heroList: HeroAPI)
    @Query("select * from table_hero_list where name = $name")
    fun getHeroList(): LiveData<HeroAPI>
}