package com.panychev.dotaheroinfoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.panychev.dotaheroinfoapp.data.db.entity.HeroAPI

@Dao
interface DaoHeroList {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(heroList: List<HeroAPI>)
    @Query("select * from table_hero_list")
    fun getHeroList(): LiveData<List<HeroAPI>>
}