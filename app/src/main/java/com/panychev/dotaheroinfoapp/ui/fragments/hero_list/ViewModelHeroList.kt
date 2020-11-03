package com.panychev.dotaheroinfoapp.ui.fragments.hero_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.panychev.dotaheroinfoapp.data.repositories.RepositoryHeroList
import com.panychev.dotaheroinfoapp.utils.lazyDeferred

class ViewModelHeroList @ViewModelInject constructor(
    private val repositoryHeroList: RepositoryHeroList
) : ViewModel() {
    val heroList by lazyDeferred {
        repositoryHeroList.getHeroList()
    }
}