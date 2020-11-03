package com.panychev.dotaheroinfoapp.ui.fragments.hero_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.data.db.entity.Hero
import com.panychev.dotaheroinfoapp.ui.fragments.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_hero_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentHeroList : ScopedFragment() {
    private val viewModel: ViewModelHeroList by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
    }

    override fun onStop() {
        super.onStop()
        activity?.finish()
    }
    private fun bindUi() = launch(Dispatchers.Main) {
        val heroLiveData = viewModel.heroList.await()
        heroLiveData.observe(viewLifecycleOwner, Observer { heroList ->
            if(heroList == null) return@Observer
            group_loading_main.visibility = View.GONE
            initRecyclerViewItem(heroList.toHeroItem())
        })
    }

    private fun List<Hero>.toHeroItem(): List<ItemHero>{
        return map {
            ItemHero(it)
        }
    }
    private fun initRecyclerViewItem(items: List<ItemHero>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }
        recycler_view_main.apply {
            layoutManager = LinearLayoutManager(this@FragmentHeroList.context)
            adapter = groupAdapter
        }
        groupAdapter.setOnItemClickListener { item, view ->
            (item as? ItemHero)?.let {
                showHeroDetail(it.hero.name, view)
            }
        }
    }
    private fun showHeroDetail(name: String, view: View){
        val actionDetail = FragmentHeroListDirections.actionDetail(name)
        Navigation.findNavController(view).navigate(actionDetail)
    }

}