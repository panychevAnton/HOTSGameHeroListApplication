package com.panychev.dotaheroinfoapp.di.modules

import android.content.Context
import com.panychev.dotaheroinfoapp.data.network.APIService
import com.panychev.dotaheroinfoapp.data.db.DaoHeroList
import com.panychev.dotaheroinfoapp.data.db.DatabaseHeroList
import com.panychev.dotaheroinfoapp.data.network.interceptors.ConnectivityInterceptor
import com.panychev.dotaheroinfoapp.data.network.interceptors.ConnectivityInterceptorImpl
import com.panychev.dotaheroinfoapp.data.network.data_source.NetworkDataSourceHeroList
import com.panychev.dotaheroinfoapp.data.network.data_source.NetworkDataSourceHeroListImpl
import com.panychev.dotaheroinfoapp.data.repositories.RepositoryHeroList
import com.panychev.dotaheroinfoapp.data.repositories.RepositoryHeroListImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModuleHeroList {
    @Provides
    @Singleton
    fun provideHeroListDatabase(
        @ApplicationContext context: Context
    ): DatabaseHeroList{
        return DatabaseHeroList.invoke(context)
    }

    @Provides
    @Singleton
    fun provideHeroListDao(
        databaseHeroList: DatabaseHeroList
    ): DaoHeroList{
        return databaseHeroList.heroListDao()
    }

    @Provides
    @Singleton
    fun provideConnectivityInterceptor(
        @ApplicationContext context: Context
    ): ConnectivityInterceptor {
        return ConnectivityInterceptorImpl(context)
    }

    @Provides
    @Singleton
    fun provideApiService(connectivityInterceptor: ConnectivityInterceptor) =
        APIService(connectivityInterceptor)

    @Provides
    @Singleton
    fun provideHeroListNetworkDataSource(
        apiService: APIService
    ): NetworkDataSourceHeroList {
        return NetworkDataSourceHeroListImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideHeroListRepository(
        daoHeroList: DaoHeroList,
        networkDataSourceHeroList: NetworkDataSourceHeroList
    ): RepositoryHeroList {
        return RepositoryHeroListImpl(daoHeroList, networkDataSourceHeroList)
    }
}