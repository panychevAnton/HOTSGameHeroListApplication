package com.panychev.dotaheroinfoapp.ui.fragments.agility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.data.APIService
import com.panychev.dotaheroinfoapp.data.network.*
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
        val apiService = APIService(ConnectivityInterceptorImpl(this.requireContext()))
        val heroListNetworkDataSource = HeroListNetworkDataSourceImpl(apiService)
        heroListNetworkDataSource.downloadedHeroList.observe(viewLifecycleOwner, {
            txt1.text = it[1].name
        })

        GlobalScope.launch(Dispatchers.Main) {
            heroListNetworkDataSource.fetchHeroList()
        }
    }

}