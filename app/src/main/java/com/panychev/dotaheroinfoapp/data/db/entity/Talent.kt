package com.panychev.dotaheroinfoapp.data.db.entity


import com.google.gson.annotations.SerializedName

data class Talent(
    val ability: String,
    val cooldown: Any,
    val description: String,
    val icon: String,
    @SerializedName("icon_url")
    val iconUrl: IconUrlX,
    val level: Int,
    @SerializedName("mana_cost")
    val manaCost: Any,
    val name: String,
    val sort: Int,
    val title: String
)