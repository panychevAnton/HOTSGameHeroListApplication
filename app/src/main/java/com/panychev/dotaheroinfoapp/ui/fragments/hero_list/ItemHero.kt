package com.panychev.dotaheroinfoapp.ui.fragments.hero_list

import com.panychev.dotaheroinfoapp.R
import com.panychev.dotaheroinfoapp.data.db.entity.Hero
import com.panychev.dotaheroinfoapp.utils.glide.GlideApp
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_hero.*

class ItemHero(
    val hero: Hero
): Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.apply {
            updateHeroAvatar()
            txt_hero_name.text = hero.name
            txt_hero_role.text = hero.role
            txt_hero_type.text = hero.type
        }
    }

    override fun getLayout() = R.layout.item_hero

    private fun GroupieViewHolder.updateHeroAvatar(){
        GlideApp.with(this.containerView)
            .load(hero.iconUrl.x93.replace("\\",""))
            .placeholder(R.drawable.img_glide_error)
            .into(img_hero_avatar)
    }
}