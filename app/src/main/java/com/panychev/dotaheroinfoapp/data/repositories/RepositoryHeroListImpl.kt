package com.panychev.dotaheroinfoapp.data.repositories

import androidx.lifecycle.LiveData
import com.panychev.dotaheroinfoapp.data.db.DaoHeroList
import com.panychev.dotaheroinfoapp.data.db.entity.Hero
import com.panychev.dotaheroinfoapp.data.network.data_source.NetworkDataSourceHeroList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class RepositoryHeroListImpl @Inject constructor(
    private val daoHeroList: DaoHeroList,
    private val networkDataSourceHeroList: NetworkDataSourceHeroList
) : RepositoryHeroList {

    init {
        networkDataSourceHeroList.downloadedHeroList.observeForever { newHeroList ->
            saveFetchedHeroList(newHeroList)
        }
    }
    override suspend fun getHeroList(): LiveData<List<Hero>> {
        return withContext(Dispatchers.IO){
            initHeroListData()
            return@withContext daoHeroList.getHeroList()
        }
    }

    override suspend fun getHeroByName(name: String): LiveData<Hero>{
        return withContext(Dispatchers.IO){
            initHeroListData()
            return@withContext daoHeroList.getHeroByName(name)
        }
    }

    private suspend fun initHeroListData() {
        if(isFetchCurrentNeed(ZonedDateTime.now().minusHours(1)))
            networkDataSourceHeroList.fetchHeroList()
    }


    private fun isFetchCurrentNeed(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

    private fun saveFetchedHeroList(fetchedHeroList: List<Hero>){
        GlobalScope.launch(Dispatchers.IO) {
            daoHeroList.upsertHeroList(fetchedHeroList)
        }
    }
}