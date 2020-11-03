package com.panychev.dotaheroinfoapp.ui.fragments.hero_detail

import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.data.network.response.Talent
import com.panychev.dotaheroinfoapp.utils.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_talent_child.*

class ItemTalentChild(
    private val talent: Talent
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            txt_talent_title.text = talent.title
            txt_talent_description.text = talent.description
            updateTalentIcon()
        }
    }

    override fun getLayout() = R.layout.item_talent_child

    private fun GroupieViewHolder.updateTalentIcon(){
        GlideApp.with(this.containerView)
            .load(talent.iconUrl.x64.replace("\\", ""))
            .into(img_talent_icon_src)
    }
}