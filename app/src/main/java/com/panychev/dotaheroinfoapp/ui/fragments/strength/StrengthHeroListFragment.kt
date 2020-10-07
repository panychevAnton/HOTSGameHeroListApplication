package com.panychev.dotaheroinfoapp.ui.fragments.strength

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.panychev.dotaheroinfoapp.R

class StrengthHeroListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_strength_hero_list, container, false)
    }
}