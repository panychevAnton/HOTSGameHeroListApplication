package com.panychev.dotaheroinfoapp.ui.fragments.hero_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.data.network.response.Talent
import com.panychev.dotaheroinfoapp.ui.fragments.base.ScopedFragment
import com.panychev.dotaheroinfoapp.utils.glide.GlideApp
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.groupiex.plusAssign
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_hero_detail.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentHeroDetail : ScopedFragment() {

    private val navigationArgs: FragmentHeroDetailArgs by navArgs()
    @Inject lateinit var assistedFactoryHeroDetail: ViewModelHeroDetail.AssistedFactory
    private val viewModelHeroDetail: ViewModelHeroDetail by viewModels{
        ViewModelHeroDetail.provideFactory(
            assistedFactoryHeroDetail, navigationArgs.name
         )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hero_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
    }
    private fun bindUi() = launch {
        val hero = viewModelHeroDetail.hero.await()
        hero.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            group_loading_detail.visibility = View.GONE
            txt_hero_detail_name.text = it.name
            txt_hero_detail_role.text = it.role
            txt_hero_detail_description.text = getString(
                R.string.hero_description,
                it.shortName,
                it.releaseDate,
                it.type
            )
            GlideApp.with(this@FragmentHeroDetail)
                .load(it.iconUrl.x93.replace("\\", ""))
                .circleCrop()
                .placeholder(R.drawable.img_glide_error)
                .into(img_hero_detail_avatar)
            initRecyclerView(it.talents)
        })
    }
    private fun Int.toItemTalentParent() = ExpandableItemTalentParent("Level $this")
    private fun Talent.toItemTalentChild() = ItemTalentChild(this)

    private fun initRecyclerView(items: List<Talent>){
        val itemsMap = items.groupBy { it.level }.toSortedMap()
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            for (i in itemsMap.keys) {
                this += ExpandableGroup(i.toItemTalentParent()).apply {
                    for (j in itemsMap[i] ?: return)
                        add(j.toItemTalentChild())
                }
            }
        }
        recycler_talents.apply {
            layoutManager = LinearLayoutManager(this@FragmentHeroDetail.context)
            adapter = groupAdapter
        }
    }
}