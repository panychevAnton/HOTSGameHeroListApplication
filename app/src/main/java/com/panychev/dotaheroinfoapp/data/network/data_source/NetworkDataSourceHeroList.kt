package com.panychev.dotaheroinfoapp.data.network.data_source

import androidx.lifecycle.LiveData
import com.panychev.dotaheroinfoapp.data.db.entity.Hero

interface NetworkDataSourceHeroList {
    val downloadedHeroList: LiveData<List<Hero>>
    suspend fun fetchHeroList()
}