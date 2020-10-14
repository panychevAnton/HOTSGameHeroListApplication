package com.panychev.dotaheroinfoapp.ui.fragments.agility

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.panychev.dotaheroinfoapp.data.repositories.RepositoryHeroList
import com.panychev.dotaheroinfoapp.utils.lazyDeferred

class ViewModelAgilityHeroList @ViewModelInject constructor(
    private val repositoryHeroList: RepositoryHeroList
) : ViewModel() {
    val heroList by lazyDeferred {
        repositoryHeroList.getHeroList()
    }
}