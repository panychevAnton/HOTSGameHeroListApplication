package com.panychev.dotaheroinfoapp.ui.fragments.agility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.ui.fragments.base.ScopedFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_agility_hero_list.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentAgilityHeroList : ScopedFragment() {
    private val viewModelAgilityHeroList: ViewModelAgilityHeroList by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agility_hero_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
    }
    private fun bindUi() = launch {
        val heroList = viewModelAgilityHeroList.heroList.await()
        heroList.observe(viewLifecycleOwner, Observer{
            if (it == null) return@Observer
            txt1.text = it[1].name
        })
    }
}