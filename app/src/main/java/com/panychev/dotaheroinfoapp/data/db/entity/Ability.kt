package com.panychev.dotaheroinfoapp.data.db.entity


import com.google.gson.annotations.SerializedName

data class Ability(
    val cooldown: Int,
    val description: String,
    val hotkey: Any,
    val icon: Any,
    @SerializedName("mana_cost")
    val manaCost: Any,
    val name: String,
    val owner: String,
    val title: String,
    val trait: Boolean
)