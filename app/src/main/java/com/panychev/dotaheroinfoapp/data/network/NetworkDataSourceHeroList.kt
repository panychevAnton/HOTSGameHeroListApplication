package com.panychev.dotaheroinfoapp.data.network

import androidx.lifecycle.LiveData
import com.panychev.dotaheroinfoapp.data.db.entity.HeroAPI

interface NetworkDataSourceHeroList {
    val downloadedHeroList: LiveData<List<HeroAPI>>

    suspend fun fetchHeroList()
}