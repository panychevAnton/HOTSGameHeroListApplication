package com.panychev.dotaheroinfoapp.ui.fragments.hero_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.panychev.dotaheroinfoapp.data.repositories.RepositoryHeroList
import com.panychev.dotaheroinfoapp.utils.lazyDeferred
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class ViewModelHeroDetail @AssistedInject constructor(
    @Assisted private val name: String,
    private val repositoryHeroList: RepositoryHeroList
) : ViewModel() {
    val hero by lazyDeferred {
        repositoryHeroList.getHeroByName(name)
    }
    @AssistedInject.Factory
    interface AssistedFactory{
        fun create(name: String): ViewModelHeroDetail
    }
    companion object{
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            assistedFactory: AssistedFactory,
            name: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(name) as T
            }
        }
    }
}