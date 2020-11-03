package com.panychev.dotaheroinfoapp.data.repositories

import androidx.lifecycle.LiveData
import com.panychev.dotaheroinfoapp.data.db.entity.Hero

interface RepositoryHeroList {
    suspend fun getHeroList(): LiveData<List<Hero>>
    suspend fun getHeroByName(name: String): LiveData<Hero>
}