package com.panychev.dotaheroinfoapp.data.network.data_source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.panychev.dotaheroinfoapp.data.db.entity.Hero
import com.panychev.dotaheroinfoapp.data.network.APIService
import com.panychev.dotaheroinfoapp.utils.NoConnectivityException
import javax.inject.Inject


class NetworkDataSourceHeroListImpl @Inject constructor(
    private val apiService: APIService
) : NetworkDataSourceHeroList {
    private val _downloadedHeroList = MutableLiveData<List<Hero>>()
    override val downloadedHeroList: LiveData<List<Hero>>
            get() = _downloadedHeroList

    override suspend fun fetchHeroList() {
        try {
            val fetchedHeroList = apiService
                .getHeroListAsync()
                .await()
            Log.i("fetchData", fetchedHeroList.toString())
            _downloadedHeroList.postValue(fetchedHeroList)
        }catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}