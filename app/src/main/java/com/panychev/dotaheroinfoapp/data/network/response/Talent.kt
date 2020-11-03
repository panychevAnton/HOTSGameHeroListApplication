package com.panychev.dotaheroinfoapp.data.network.response


import com.google.gson.annotations.SerializedName

data class Talent(
    val ability: String,
    val description: String,
    @SerializedName("icon_url")
    val iconUrl: IconUrlX,
    val level: Int,
    @SerializedName("mana_cost")
    val name: String,
    val title: String,
)