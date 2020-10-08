package com.panychev.dotaheroinfoapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.panychev.dotaheroinfoapp.data.APIService
import com.panychev.dotaheroinfoapp.data.db.entity.HeroAPI
import com.panychev.dotaheroinfoapp.utils.NoConnectivityException

class HeroListNetworkDataSourceImpl(
    private val apiService: APIService
) : HeroListNetworkDataSource {
    private val _downloadedHeroList = MutableLiveData<List<HeroAPI>>()
    override val downloadedHeroList: LiveData<List<HeroAPI>>
            get() = _downloadedHeroList

    override suspend fun fetchHeroList() {
        try {
            val fetchedHeroList = apiService
                .getHeroList()
                .await()
            _downloadedHeroList.postValue(fetchedHeroList)
        }catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}