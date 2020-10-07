package com.panychev.dotaheroinfoapp.ui.fragments.intellect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.panychev.dotaheroinfoapp.R

class IntellectHeroListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_intellect_hero_list, container, false)
    }

}