package com.panychev.dotaheroinfoapp.ui.fragments.hero_detail

import com.panychev.dotaheroinfoapp.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_talent_parent.*


class ExpandableItemTalentParent(
    private val title: String
): Item(), ExpandableItem {
    private lateinit var expandableGroup: ExpandableGroup
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            txt_talent_level.text = title
            itemView.setOnClickListener {
                expandableGroup.onToggleExpanded()
                updateIcon()
            }
        }
    }

    override fun getLayout() = R.layout.item_talent_parent

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }

    private fun GroupieViewHolder.updateIcon(){
        img_talent_expand_icon.setImageResource(
            if(expandableGroup.isExpanded)
                R.drawable.icon_expand_less
            else
                R.drawable.icon_expand_more)
    }
}