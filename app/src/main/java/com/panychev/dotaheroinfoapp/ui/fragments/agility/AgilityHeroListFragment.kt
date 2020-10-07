package com.panychev.dotaheroinfoapp.ui.fragments.agility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.data.APIService
import kotlinx.android.synthetic.main.fragment_agility_hero_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AgilityHeroListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agility_hero_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiService = APIService()
        GlobalScope.launch(Dispatchers.Main) {
            val heroes = apiService.getHeroList().await()
            txt1.text = heroes[1].name
        }
    }

}