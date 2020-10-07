package com.panychev.dotaheroinfoapp.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.panychev.dotaheroinfoapp.data.db.entity.HeroAPI
import com.panychev.dotaheroinfoapp.data.network.ConnectivityInterceptor
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("heroes")
    fun getHeroList(): Deferred<List<HeroAPI>>
    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): APIService{
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://hotsapi.net/api/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)

        }
    }
}